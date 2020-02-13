package com.benmohammad.mvp_rxjava.di.component;

import com.benmohammad.mvp_rxjava.di.ActivityScope;
import com.benmohammad.mvp_rxjava.di.module.ActivityModule;
import com.benmohammad.mvp_rxjava.presentation.authentication.LoginFragment;
import com.benmohammad.mvp_rxjava.presentation.authentication.RegisterFragment;
import com.benmohammad.mvp_rxjava.presentation.main.CreateTodoFragment;
import com.benmohammad.mvp_rxjava.presentation.main.MainActivity;
import com.benmohammad.mvp_rxjava.presentation.splash.SplashActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(CreateTodoFragment createTodoFragment);
    void inject(SplashActivity splashActivity);
    void inject(LoginFragment loginFragment);
    void inject(RegisterFragment registerFragment);
}
