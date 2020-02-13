package com.benmohammad.mvp_rxjava.presentation.splash;

import android.os.Handler;
import android.widget.ProgressBar;

import com.benmohammad.mvp_rxjava.R;
import com.benmohammad.mvp_rxjava.presentation.authentication.AuthenticationActivity;
import com.benmohammad.mvp_rxjava.presentation.base.BaseActivity;
import com.benmohammad.mvp_rxjava.presentation.main.MainActivity;
import com.benmohammad.mvp_rxjava.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @BindView(R.id.progress_splash)
    ProgressBar progressBar;

    @Inject
    SplashPresenter splashPresenter;

    private Handler mHandler;



    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initUi() {
        getSupportActionBar().hide();
        mHandler = new Handler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        splashPresenter.attachView(this);
        showSplash();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashPresenter.detachView();
    }

    private void showSplash() {
        Runnable runnable = () -> splashPresenter.authenticate();
        mHandler.postDelayed(runnable, Constants.SPLASH_TIME);
    }

    @Override
    public void navigateToMainScreen() {
        start(MainActivity.class);
        finish();
    }

    @Override
    public void navigateToAuthenticationScreen() {
        start(AuthenticationActivity.class);
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


}
