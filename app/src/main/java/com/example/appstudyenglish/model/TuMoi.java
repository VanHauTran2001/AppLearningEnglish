package com.example.appstudyenglish.model;

public class TuMoi {
    private String content;
    private int status;

    public TuMoi(){}

    public TuMoi(String content, int status) {
        this.content = content;
        this.status = status;
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
