package com.benmohammad.mvp_rxjava.presentation.splash;


import com.benmohammad.mvp_rxjava.data.UserRepository;
import com.benmohammad.mvp_rxjava.data.network.model.AuthResponse;
import com.benmohammad.mvp_rxjava.di.ActivityScope;
import com.benmohammad.mvp_rxjava.presentation.base.Presenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class SplashPresenter extends Presenter<SplashContract.View> implements SplashContract.Presenter {

    private UserRepository userRepository;
    @Inject
    public SplashPresenter(CompositeDisposable compositeDisposable, UserRepository userRepository) {
        super(compositeDisposable);
        this.userRepository = userRepository;
    }



    @Override
    public void authenticate() {
        getCompositeDisposable()
                .add(userRepository.authenticate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::authenticationSuccess, this::authenticationFailure));

    }

    private void authenticationSuccess(AuthResponse authResponse) {
        getView().navigateToMainScreen();
    }

    private void authenticationFailure(Throwable error) {
        getView().navigateToAuthenticationScreen();
    }
}
