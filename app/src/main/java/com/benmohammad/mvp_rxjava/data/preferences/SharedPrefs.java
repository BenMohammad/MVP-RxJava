package com.benmohammad.mvp_rxjava.data.preferences;

public interface SharedPrefs {

    void setCurrentUserId(String userId);

    String getCurrentUserId();

    void setToken(String token);

    String getToken();

    void removeCredentials();
}
