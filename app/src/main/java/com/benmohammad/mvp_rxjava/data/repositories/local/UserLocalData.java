package com.benmohammad.mvp_rxjava.data.repositories.local;

import com.benmohammad.mvp_rxjava.data.preferences.SharedPrefs;

import javax.inject.Inject;

import io.reactivex.Completable;

public class UserLocalData {

    private SharedPrefs sharedPrefs;

    @Inject
    public UserLocalData(SharedPrefs sharedPrefs) {
        this.sharedPrefs = sharedPrefs;
    }

    public void setCurrentUserId(String userId) {
        sharedPrefs.setCurrentUserId(userId);
    }

    public String getCurrentUserid() {
        return sharedPrefs.getCurrentUserId();
    }

    public String getToken() {
        return sharedPrefs.getToken();
    }

    public void setToken(String token) {
        sharedPrefs.setToken(token);
    }

    public Completable logout() {
        sharedPrefs.removeCredentials();
        return Completable.complete();
    }
}
