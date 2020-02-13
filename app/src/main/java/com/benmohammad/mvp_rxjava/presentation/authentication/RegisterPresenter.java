package com.benmohammad.mvp_rxjava.presentation.authentication;

import com.benmohammad.mvp_rxjava.data.UserRepository;
import com.benmohammad.mvp_rxjava.data.network.model.AuthResponse;
import com.benmohammad.mvp_rxjava.data.network.model.RegisterRequest;
import com.benmohammad.mvp_rxjava.di.ActivityScope;
import com.benmohammad.mvp_rxjava.presentation.base.Presenter;
import com.benmohammad.mvp_rxjava.utils.ErrorUtils;
import com.benmohammad.mvp_rxjava.utils.ValidationUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class RegisterPresenter extends Presenter<RegisterContract.View> implements RegisterContract.Presenter {

    private UserRepository userRepository;

    @Inject
    public RegisterPresenter(CompositeDisposable compositeDisposable, UserRepository userRepository) {
        super(compositeDisposable);
        this.userRepository = userRepository;
    }

    @Override
    public void register(String email, String password, String confirmPassword, String userName) {
        if(ValidationUtils.isNullOrEmpty(email, password)) {
            return;
        }
        if(!ValidationUtils.isValidEmail(email)) {
            getView().setErrorEmailField();
            return;
        }
        if(!ValidationUtils.isValidPassword(password)) {
            getView().setErrorPasswordField();
            return;
        }

        if(!password.equals(confirmPassword)) {
            getView().setErrorConfirmPasswordField();
            return;
        }

        getView().showLoading();
        getCompositeDisposable()
                .add(userRepository.registerUser(new RegisterRequest(email, password, userName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::registerSuccess, this::registerFailure));
    }
    private void registerSuccess(AuthResponse authResponse) {
        getView().hideLoading();
        getView().navigateToMainScreen();
    }

    private void registerFailure(Throwable error) {
        getView().hideLoading();
        getView().registerFailure(ErrorUtils.getErrorMessage(error));
        error.printStackTrace();

    }

}
