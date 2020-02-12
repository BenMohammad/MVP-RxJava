package com.benmohammad.mvp_rxjava.data;

import com.google.gson.annotations.SerializedName;

public class Todo {

    @SerializedName("id")
    public String id;

    @SerializedName("_creator")
    private String creator;

    @SerializedName("text")
    private String text;

    @SerializedName("createDate")
    private String createDate;

    @SerializedName("completed")
    private boolean completed;

    @SerializedName("completedAt")
    private String completedAt;

    public Todo(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }
}
