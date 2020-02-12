package com.benmohammad.mvp_rxjava;

import android.app.Application;
import android.content.Context;

import com.benmohammad.mvp_rxjava.di.component.AppComponent;

public class App extends Application {

    private AppComponent mAppComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }


    public AppComponent component() {
        return mAppComponent;
    }
}
