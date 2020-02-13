package com.benmohammad.mvp_rxjava.presentation.authentication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.benmohammad.mvp_rxjava.R;
import com.benmohammad.mvp_rxjava.presentation.base.BaseActivity;
import com.benmohammad.mvp_rxjava.utils.Constants;

public class AuthenticationActivity extends BaseActivity {

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            replaceToLoginFragment();
        }

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_authentication;
    }

    @Override
    protected void inject() {

    }

    @Override
    protected void initUi() {
        fragmentManager = getSupportFragmentManager();
    }

    private void replaceToLoginFragment() {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_enter)
                .replace(R.id.frame_container, new LoginFragment(), Constants.LOGIN_FRAGMENT)
                .commit();
    }

    @Override
    public void onBackPressed() {
        Fragment registerFragment = fragmentManager.findFragmentByTag(Constants.REGISTER_FRAGMENT);
        if(registerFragment != null) {
            replaceToLoginFragment();
        } else {
            super.onBackPressed();
        }
    }
}
