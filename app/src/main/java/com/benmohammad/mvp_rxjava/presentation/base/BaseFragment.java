package com.benmohammad.mvp_rxjava.presentation.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.benmohammad.mvp_rxjava.App;
import com.benmohammad.mvp_rxjava.di.component.ActivityComponent;
import com.benmohammad.mvp_rxjava.di.component.DaggerActivityComponent;
import com.benmohammad.mvp_rxjava.di.module.ActivityModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {


    private Unbinder unbinder;
    private BaseActivity mActivity;
    private ActivityComponent activityComponent;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         activityComponent = DaggerActivityComponent.builder()
                .appComponent(App.get(getActivity()).component())
                .activityModule(new ActivityModule(getActivity()))
                .build();

         inject();
         initUi();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }
    }

    protected void start(Class<? extends BaseActivity> activity) {
        startActivity(new Intent(getActivity(), activity));
    }

    protected abstract int getFragmentLayout();

    protected abstract  void initUi();

    protected abstract void inject();

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public void showSnackBar(String message) {
        if(mActivity != null) {
            mActivity.showSnackBar(message);
        }
    }

}
