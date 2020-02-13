package com.benmohammad.mvp_rxjava.presentation.main;


import com.benmohammad.mvp_rxjava.data.TodoRepository;
import com.benmohammad.mvp_rxjava.data.network.model.TodoRequest;
import com.benmohammad.mvp_rxjava.di.ActivityScope;
import com.benmohammad.mvp_rxjava.presentation.base.Presenter;
import com.benmohammad.mvp_rxjava.utils.ValidationUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class CreateTodoPresenter extends Presenter<CreateTodoContract.View> implements CreateTodoContract.Presenter{

    private TodoRepository todoRepository;

    @Inject
    public CreateTodoPresenter(TodoRepository todoRepository, CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
        this.todoRepository = todoRepository;
    }
    @Override
    public void saveTodo(String text) {
        if(!ValidationUtils.isValidText(text)) {
            getView().setErrorTextField();
            return;
        }
        getView().showLoading();
        getCompositeDisposable()
                .add(todoRepository.saveTodo(new TodoRequest(text))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::saveTodoSuccess, this::saveTodoFailure));
    }

    private void saveTodoSuccess() {
        getView().hideLoading();
        getView().navigateToMainScreen();
    }
    private void saveTodoFailure(Throwable error) {
        error.printStackTrace();
        getView().hideLoading();
        getView().saveTodoFailure();
    }
}
