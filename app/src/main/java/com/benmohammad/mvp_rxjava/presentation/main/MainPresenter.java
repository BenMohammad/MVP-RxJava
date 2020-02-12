package com.benmohammad.mvp_rxjava.presentation.main;

import com.benmohammad.mvp_rxjava.data.TodoRepository;
import com.benmohammad.mvp_rxjava.data.UserRepository;
import com.benmohammad.mvp_rxjava.data.model.Todo;
import com.benmohammad.mvp_rxjava.di.ActivityScope;
import com.benmohammad.mvp_rxjava.presentation.base.Presenter;
import com.benmohammad.mvp_rxjava.utils.ErrorUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class MainPresenter extends Presenter<MainContract.View> implements MainContract.Presenter {

    private TodoRepository todoRepository;
    private UserRepository userRepository;

    @Inject
    public MainPresenter(TodoRepository todoRepository, UserRepository userRepository, CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }



    @Override
    public void getTodos() {
        getView().showLoading();
        getCompositeDisposable()
                .add(todoRepository.getAllTodos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getTodoSuccess, this::getTodosFailure));
    }

    @Override
    public void updateTodo(String id) {
        getView().showLoading();
        getCompositeDisposable()
                .add(todoRepository.updateTodoById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateTodoSuccess, this::updateTodoFailure));
    }

    @Override
    public void removeTodo(String id) {
        getView().showLoading();
        getCompositeDisposable()
                .add(todoRepository.removeTodoById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::removeTodoSuccess, this::removeTodoFailure));
    }

    @Override
    public void logout() {
        getCompositeDisposable()
                .add(userRepository.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getView().navigateToAuthenticationScreen()));
    }


    private void getTodoSuccess(List<Todo> todoList) {
        if(todoList.isEmpty()) {
            getView().hideLoading();
            getView().showEmpty();
        } else {
            getView().hideLoading();
            getView().showTodos(todoList);
        }
    }

    private void getTodosFailure(Throwable error) {
        error.printStackTrace();
        getView().hideLoading();
        getView().showTodosFailure(ErrorUtils.getErrorMessage(error));
        error.printStackTrace();
    }

    private void updateTodoSuccess() {
        getView().hideLoading();
        getView().updateTodoSuccess();
    }

    private void updateTodoFailure(Throwable error) {
        error.printStackTrace();
        getView().hideLoading();
        getView().updateTodoFailure(ErrorUtils.getErrorMessage(error));

    }

    private void removeTodoSuccess() {
        getView().hideLoading();
        getView().removeTodoSuccess();
    }

    private void removeTodoFailure(Throwable error) {
        error.printStackTrace();
        getView().hideLoading();
        getView().removeTodosFailure(ErrorUtils.getErrorMessage(error));

    }

}
