package com.benmohammad.mvp_rxjava.utils;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.NonNull;

public class ValidationUtils {

    public static boolean isNullOrEmpty(String string1, String string2) {
        return TextUtils.isEmpty(string1) && TextUtils.isEmpty(string2);
    }

    public static boolean isValidPassword(@NonNull String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }

    public static boolean isValidEmail(@NonNull String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidText(@NonNull String text) {
        return !TextUtils.isEmpty(text) && text.length() >= 5;
    }
}
