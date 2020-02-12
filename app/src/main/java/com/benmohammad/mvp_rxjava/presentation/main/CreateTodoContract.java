package com.benmohammad.mvp_rxjava.presentation.main;

import com.benmohammad.mvp_rxjava.presentation.base.BaseView;

public interface CreateTodoContract {

    interface View extends BaseView {
        void setErrorTextField();
        void saveTodoFailure();
        void navigateToMainScreen();
    }

    interface Presenter {
        void saveTodo(String text);
    }
}
