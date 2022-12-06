package com.example.appstudyenglish.model;

import java.io.Serializable;

public class Tuan implements Serializable {
    private String title;
    private int status;

    public Tuan(){}

    public Tuan(String title, int status) {
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
