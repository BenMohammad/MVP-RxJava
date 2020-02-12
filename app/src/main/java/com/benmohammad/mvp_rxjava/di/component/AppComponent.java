package com.benmohammad.mvp_rxjava.di.component;


import android.content.Context;

import com.benmohammad.mvp_rxjava.App;
import com.benmohammad.mvp_rxjava.data.TodoRepository;
import com.benmohammad.mvp_rxjava.data.UserRepository;
import com.benmohammad.mvp_rxjava.data.network.services.TodoService;
import com.benmohammad.mvp_rxjava.data.preferences.SharedPrefs;
import com.benmohammad.mvp_rxjava.di.ApplicationContext;
import com.benmohammad.mvp_rxjava.di.module.AppModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Gson gson();

    Retrofit retrofit();

    SharedPrefs sharedPrefs();

    TodoService todoService();

    TodoRepository todorepository();

    UserRepository userRepository();
}
