package com.example.appstudyenglish.model;

import java.io.Serializable;

public class KhoaHoc implements Serializable {
    private String maKhoaHoc;
    private int avatar;
    private String name;
    private String date;
    private int viewer;
    private String title;

    public KhoaHoc(){}

    public KhoaHoc(String maKhoaHoc, int avatar, String name, String date, int viewer, String title) {
        this.maKhoaHoc = maKhoaHoc;
        this.avatar = avatar;
        this.name = name;
        this.date = date;
        this.viewer = viewer;
        this.title = title;
    }

    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(String maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getViewer() {
        return viewer;
    }

    public void setViewer(int viewer) {
        this.viewer = viewer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
