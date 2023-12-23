package com.ecostore.model;

public class FeedBackModel extends AbstractModel {
    private long userId;
    private UserModel user;
    private String content;
    private int status;

    public FeedBackModel() {
    }

    public UserModel getUser() {
        return user;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUser(UserModel userId) {
        this.user = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
