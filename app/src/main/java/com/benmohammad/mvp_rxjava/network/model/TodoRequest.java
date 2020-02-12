package com.benmohammad.mvp_rxjava.network.model;

import com.google.gson.annotations.SerializedName;

public class TodoRequest {

    @SerializedName("text")
    private String text;

    public TodoRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
