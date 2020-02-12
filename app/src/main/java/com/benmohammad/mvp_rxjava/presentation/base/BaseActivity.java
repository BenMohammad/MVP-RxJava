package com.benmohammad.mvp_rxjava.presentation.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.benmohammad.mvp_rxjava.App;
import com.benmohammad.mvp_rxjava.R;
import com.benmohammad.mvp_rxjava.di.component.ActivityComponent;

import com.benmohammad.mvp_rxjava.di.component.DaggerActivityComponent;
import com.benmohammad.mvp_rxjava.di.module.ActivityModule;
import com.google.android.material.snackbar.Snackbar;

public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = getClass().getSimpleName();
    ActivityComponent mActivityComponent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(App.get(this).component())
                .activityModule(new ActivityModule(this))
                .build();

        inject();
        initUi();
    }

    protected abstract @LayoutRes int getLayout();

    protected abstract void inject();

    protected abstract void initUi();

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    protected void start(Class<? extends BaseActivity> activity) {
        startActivity(new Intent(this, activity));
    }

    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView
                .findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        snackbar.show();
    }



}
