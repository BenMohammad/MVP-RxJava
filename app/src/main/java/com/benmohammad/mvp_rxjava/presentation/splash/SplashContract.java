package com.benmohammad.mvp_rxjava.presentation.splash;

import com.benmohammad.mvp_rxjava.presentation.base.BaseView;

public interface SplashContract {

    interface View extends BaseView {
        void navigateToMainScreen();
        void navigateToAuthenticationScreen();
    }

    interface Presenter {
        void authenticate();
    }
}
