package com.benmohammad.mvp_rxjava.presentation.authentication;

import android.app.ProgressDialog;
import android.media.tv.TvContract;

import androidx.fragment.app.FragmentManager;

import com.benmohammad.mvp_rxjava.R;
import com.benmohammad.mvp_rxjava.presentation.base.BaseFragment;
import com.benmohammad.mvp_rxjava.presentation.main.MainActivity;
import com.benmohammad.mvp_rxjava.utils.Constants;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginContract.View {


    @BindView(R.id.til_email)
    TextInputLayout tilEmail;

    @BindView(R.id.til_password)
    TextInputLayout tilPassword;

    @Inject
    LoginPresenter loginPresenter;

    private ProgressDialog progressDialog;
    private FragmentManager fragmentManager;

    @Override
    public void loginFailure(String error) {
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
    public void navigateToMainScreen() {
        start(MainActivity.class);
        getActivity().finish();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initUi() {
        fragmentManager = getFragmentManager();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.signin_loading));
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        loginPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }


    @OnClick(R.id.login_btn)
    public void loginClick() {
        String email = tilEmail.getEditText().getText().toString();
        String password = tilPassword.getEditText().getText().toString();
        loginPresenter.login(email, password);
    }

    @OnClick(R.id.create_account)
    public void createAccountClick() {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                .replace(R.id.frame_container, new RegisterFragment(), Constants.REGISTER_FRAGMENT)
                .commit();
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
