package com.benmohammad.mvp_rxjava.data.repositories.local;

import com.benmohammad.mvp_rxjava.data.preferences.SharedPrefs;

import javax.inject.Inject;

public class TodoLocalData {

    private SharedPrefs sharedPrefs;

    @Inject
    public TodoLocalData(SharedPrefs sharedPrefs) {
        this.sharedPrefs = sharedPrefs;
    }
}
