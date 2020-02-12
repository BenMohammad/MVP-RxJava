package com.benmohammad.mvp_rxjava.presentation.authentication;

import android.app.ProgressDialog;

import com.benmohammad.mvp_rxjava.R;
import com.benmohammad.mvp_rxjava.presentation.base.BaseFragment;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;

public class RegisterFragment extends BaseFragment implements RegisterContract.View {

    @BindView(R.id.layout_input_email)
    TextInputLayout tilEmail;

    @BindView(R.id.layout_input_username)
    TextInputLayout tilUserName;

    @BindView(R.id.layout_input_password)
    TextInputLayout tilPassword;

    @BindView(R.id.layout_input_confirm_password)
    TextInputLayout tilCOnfirmPassword;


    @Inject
    RegisterPresenter registerPresenter;

    private ProgressDialog progressDialog;


    @Override
    public void registerFailure(String error) {

    }

    @Override
    public void setErrorEmailField() {

    }

    @Override
    public void setErrorPasswordField() {

    }

    @Override
    public void setErrorConfirmPasswordField() {

    }

    @Override
    public void navigateToMainScreen() {

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initUi() {

    }

    @Override
    protected void inject() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
