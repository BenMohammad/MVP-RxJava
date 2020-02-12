package com.benmohammad.mvp_rxjava.di.component;

import com.benmohammad.mvp_rxjava.di.ActivityScope;
import com.benmohammad.mvp_rxjava.di.module.ActivityModule;
import com.benmohammad.mvp_rxjava.presentation.main.CreateTodoFragment;
import com.benmohammad.mvp_rxjava.presentation.main.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(CreateTodoFragment createTodoFragment);
    
}
