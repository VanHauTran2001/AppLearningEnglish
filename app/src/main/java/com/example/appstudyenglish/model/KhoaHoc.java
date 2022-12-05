package com.example.appstudyenglish.model;

public class KhoaHoc {
    private String maKhoaHoc;
    private int avatar;
    private String name;
    private String date;
    private int viewer;

    public KhoaHoc(){}

    public KhoaHoc(String maKhoaHoc, int avatar, String name, String date, int viewer) {
        this.maKhoaHoc = maKhoaHoc;
        this.avatar = avatar;
        this.name = name;
        this.date = date;
        this.viewer = viewer;
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
}
