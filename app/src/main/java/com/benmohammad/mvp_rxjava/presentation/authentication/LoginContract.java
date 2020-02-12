package com.benmohammad.mvp_rxjava.presentation.authentication;

import com.benmohammad.mvp_rxjava.presentation.base.BaseView;

public interface LoginContract {

    interface View extends BaseView {
        void loginFailure(String error);
        void setErrorEmailField();
        void setErrorPasswordField();
        void navigateToMainScreen();
    }

    interface Presenter {
        void login(String email, String password);
    }
}
