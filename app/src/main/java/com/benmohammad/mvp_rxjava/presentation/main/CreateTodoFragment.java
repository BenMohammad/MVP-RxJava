package com.benmohammad.mvp_rxjava.presentation.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.benmohammad.mvp_rxjava.App;
import com.benmohammad.mvp_rxjava.R;
import com.benmohammad.mvp_rxjava.di.component.DaggerActivityComponent;
import com.benmohammad.mvp_rxjava.di.module.ActivityModule;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;


public class CreateTodoFragment extends DialogFragment implements CreateTodoContract.View {

    TextInputLayout tilText;
    private ProgressDialog mProgressDialog;
    private Button btnSave;
    @Inject
    CreateTodoPresenter mPresenter;

    public CreateTodoFragment() {

    }

    public static CreateTodoFragment newInstance() {
        return new CreateTodoFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
        DaggerActivityComponent.builder()
                .appComponent(App.get(getActivity()).component())
                .activityModule(new ActivityModule(getActivity()))
                .build()
                .inject(this);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getResources().getString(R.string.saving_text));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_todo, container, false);
        tilText = view.findViewById(R.id.til_text);
        btnSave = view.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(v -> {
            String text = tilText.getEditText().getText().toString();
            mPresenter.saveTodo(text);
        });
        return view;
    }

    @Override
    public void onResume() {
        mPresenter.attachView(this);
        super.onResume();
    }



    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void setErrorTextField() {
        tilText.setError(getActivity().getString(R.string.error));
    }

    @Override
    public void saveTodoFailure() {
        Toast.makeText(getActivity(), getActivity().getString(R.string.some_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToMainScreen() {
        if(getActivity() instanceof MainActivity) {
            ((MainActivity)getActivity()).load();
            dismiss();
        }
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.hide();
    }


}
