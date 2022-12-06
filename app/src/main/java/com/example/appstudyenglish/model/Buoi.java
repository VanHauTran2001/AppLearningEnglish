package com.example.appstudyenglish.model;

public class Buoi {
    private String tenBuoi;
    private String phan1;
    private String tienDo1;
    private String phan2;
    private String tienDo2;
    private String phan3;
    private String tienDo3;
    private int checkLoai;

    public Buoi(){

    }

    public Buoi(String tenBuoi, String phan1, String tienDo1, String phan2, String tienDo2, String phan3, String tienDo3, int checkLoai) {
        this.tenBuoi = tenBuoi;
        this.phan1 = phan1;
        this.tienDo1 = tienDo1;
        this.phan2 = phan2;
        this.tienDo2 = tienDo2;
        this.phan3 = phan3;
        this.tienDo3 = tienDo3;
        this.checkLoai = checkLoai;
    }

    public String getTenBuoi() {
        return tenBuoi;
    }

    public void setTenBuoi(String tenBuoi) {
        this.tenBuoi = tenBuoi;
    }

    public String getPhan1() {
        return phan1;
    }

    public void setPhan1(String phan1) {
        this.phan1 = phan1;
    }

    public String getTienDo1() {
        return tienDo1;
    }

    public void setTienDo1(String tienDo1) {
        this.tienDo1 = tienDo1;
    }

    public String getPhan2() {
        return phan2;
    }

    public void setPhan2(String phan2) {
        this.phan2 = phan2;
    }

    public String getTienDo2() {
        return tienDo2;
    }

    public void setTienDo2(String tienDo2) {
        this.tienDo2 = tienDo2;
    }

    public String getPhan3() {
        return phan3;
    }

    public void setPhan3(String phan3) {
        this.phan3 = phan3;
    }

    public String getTienDo3() {
        return tienDo3;
    }

    public void setTienDo3(String tienDo3) {
        this.tienDo3 = tienDo3;
    }

    public int getCheckLoai() {
        return checkLoai;
    }

    public void setCheckLoai(int checkLoai) {
        this.checkLoai = checkLoai;
    }
}
