package com.benmohammad.mvp_rxjava.presentation.authentication;

import com.benmohammad.mvp_rxjava.data.UserRepository;
import com.benmohammad.mvp_rxjava.data.network.model.AuthResponse;
import com.benmohammad.mvp_rxjava.data.network.model.LoginRequest;
import com.benmohammad.mvp_rxjava.di.ActivityScope;
import com.benmohammad.mvp_rxjava.presentation.base.Presenter;
import com.benmohammad.mvp_rxjava.utils.ErrorUtils;
import com.benmohammad.mvp_rxjava.utils.ValidationUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class LoginPresenter extends Presenter<LoginContract.View> implements LoginContract.Presenter {

    private UserRepository userRepository;

    @Inject
    public LoginPresenter(CompositeDisposable compositeDisposable, UserRepository userRepository) {
        super(compositeDisposable);
        this.userRepository = userRepository;
    }



    @Override
    public void login(String email, String password) {
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

        getView().showLoading();
        getCompositeDisposable()
                .add(userRepository.login(new LoginRequest(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loginSuccess, this::loginFailure));
    }

    private void loginSuccess(AuthResponse authResponse) {
        getView().hideLoading();
        getView().navigateToMainScreen();
    }


    private void loginFailure(Throwable error) {
        getView().hideLoading();
        getView().loginFailure(ErrorUtils.getErrorMessage(error));
        error.printStackTrace();
    }
}
