package com.benmohammad.mvp_rxjava.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.benmohammad.mvp_rxjava.di.ApplicationContext;
import com.benmohammad.mvp_rxjava.utils.Constants;

import javax.inject.Inject;

public class SharedPrefsHelper implements SharedPrefs {

    private static final String KEY_TOKEN = "PREFS_KEY_TOKEN";
    private static final String KEY_CURRENT_USER_ID = "PREFS_KEY_USER_ID";


    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPrefsHelper(@ApplicationContext Context context) {
        this.sharedPreferences = context.getSharedPreferences(
                Constants.PREF_FILE_NAME, Context.MODE_PRIVATE
        );
    }

    @Override
    public void setCurrentUserId(String userId) {
        sharedPreferences.edit().putString(KEY_CURRENT_USER_ID, userId).apply();
    }

    @Override
    public String getCurrentUserId() {
        return sharedPreferences.getString(KEY_CURRENT_USER_ID, null);
    }

    @Override
    public void setToken(String token) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply();
    }

    @Override
    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    @Override
    public void removeCredentials() {
        sharedPreferences.edit()
                .remove(KEY_TOKEN)
                .remove(KEY_CURRENT_USER_ID)
                .apply();
    }
}
