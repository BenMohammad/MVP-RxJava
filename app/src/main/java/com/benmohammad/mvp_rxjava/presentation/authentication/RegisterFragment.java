package com.benmohammad.mvp_rxjava.presentation.authentication;

import android.app.ProgressDialog;

import com.benmohammad.mvp_rxjava.R;
import com.benmohammad.mvp_rxjava.presentation.base.BaseFragment;
import com.benmohammad.mvp_rxjava.presentation.main.MainActivity;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment implements RegisterContract.View {

    @BindView(R.id.layout_input_email)
    TextInputLayout tilEmail;

    @BindView(R.id.layout_input_username)
    TextInputLayout tilUserName;

    @BindView(R.id.layout_input_password)
    TextInputLayout tilPassword;

    @BindView(R.id.layout_input_confirm_password)
    TextInputLayout tilConfirmPassword;


    @Inject
    RegisterPresenter registerPresenter;

    private ProgressDialog progressDialog;




    @Override
    public void registerFailure(String error) {
        showSnackBar(error);
    }

    @Override
    public void setErrorEmailField() {
        tilEmail.setError(getString(R.string.invalid_email));
    }

    @Override
    public void setErrorPasswordField() {
        tilPassword.setError(getString(R.string.invalid_password));
    }

    @Override
    public void setErrorConfirmPasswordField() {
        tilConfirmPassword.setError(getString(R.string.doesnt_match));
    }

    @Override
    public void navigateToMainScreen() {
        start(MainActivity.class);
        getActivity().finish();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initUi() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setInverseBackgroundForced(true);
        progressDialog.setMessage(getResources().getString(R.string.signup_loading));
    }

    @OnClick(R.id.btn_sign_up)
    public void signUpClick() {
        String email = tilEmail.getEditText().getText().toString();
        String userName = tilUserName.getEditText().getText().toString();
        String password = tilPassword.getEditText().getText().toString();
        String confirmPassword = tilConfirmPassword.getEditText().getText().toString();

        registerPresenter.register(email, password, confirmPassword, userName);

    }

    @Override
    public void onResume() {
        super.onResume();
        registerPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        registerPresenter.detachView();
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.hide();
    }
}
