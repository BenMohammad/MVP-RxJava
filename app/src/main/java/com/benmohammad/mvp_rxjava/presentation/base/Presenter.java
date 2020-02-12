package com.benmohammad.mvp_rxjava.presentation.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class Presenter<T extends BaseView> {

    private T mView;
    private CompositeDisposable compositeDisposable;

    public Presenter(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }

    public T getView() {
        return mView;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public void attachView(T view) {
        mView = view;
    }

    public void detachView(){
        mView = null;
        compositeDisposable.dispose();
    }
}
