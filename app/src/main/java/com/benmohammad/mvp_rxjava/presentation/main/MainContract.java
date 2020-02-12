package com.benmohammad.mvp_rxjava.presentation.main;

import com.benmohammad.mvp_rxjava.data.model.Todo;
import com.benmohammad.mvp_rxjava.presentation.base.BaseView;

import java.util.List;

public interface MainContract {

    interface View extends BaseView {
        void showTodos(List<Todo> todoList);
        void showTodosFailure(String error);
        void updateTodoSuccess();
        void updateTodoFailure(String message);
        void removeTodoSuccess();
        void removeTodosFailure(String message);
        void showEmpty();
        void navigateToAuthenticationScreen();
    }

    interface Presenter {
        void getTodos();
        void updateTodo(String id);
        void removeTodo(String id);
        void logout();

    }

}
