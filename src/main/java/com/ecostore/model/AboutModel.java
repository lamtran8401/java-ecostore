package com.ecostore.model;

public class AboutModel extends AbstractModel {
    private String content;
    private int status;

    public AboutModel() {
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
