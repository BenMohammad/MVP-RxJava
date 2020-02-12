package com.benmohammad.mvp_rxjava.utils;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ErrorUtils {

    private static final String NETWORK_CONNECTION_ERROR = "No Internet Connection";
    private static final String DEFAULT_ERROR = "Error occurred";

    public static String getErrorMessage(Throwable error) {

        if(error instanceof HttpException) {
            ResponseBody responseBody = ((HttpException)error).response().errorBody();
            try {
                JSONObject jsonObject = new JSONObject(responseBody.string());
                return jsonObject.getString("error");
            } catch (Exception e) {
                e.getMessage();
            }
        }

        if(error instanceof IOException) {
            return NETWORK_CONNECTION_ERROR;
        }
        return DEFAULT_ERROR;
    }
}
