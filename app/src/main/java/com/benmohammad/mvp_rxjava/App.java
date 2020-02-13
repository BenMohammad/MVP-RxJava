package com.benmohammad.mvp_rxjava;

import android.app.Application;
import android.content.Context;

import com.benmohammad.mvp_rxjava.di.component.AppComponent;
import com.benmohammad.mvp_rxjava.di.component.DaggerAppComponent;
import com.benmohammad.mvp_rxjava.di.module.AppModule;

public class App extends Application {

    private AppComponent mAppComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }


    public AppComponent component() {
        return mAppComponent;
    }
}
