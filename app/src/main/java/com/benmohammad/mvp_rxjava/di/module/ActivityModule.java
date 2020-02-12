package com.benmohammad.mvp_rxjava.di.module;

import android.app.Activity;

import com.benmohammad.mvp_rxjava.presentation.adapters.TodoAdapter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    Activity activity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    TodoAdapter provideTodoAdapter() {
        return new TodoAdapter(mActivity);
    }
}
