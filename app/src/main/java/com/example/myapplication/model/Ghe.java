package com.example.myapplication.model;

public class Ghe {
    private int gID;
    private String tenGhe, loaiGhe, tenXe;
    private int tinhTrang;


    public Ghe() {
    }

    public Ghe(int gID, String tenGhe, String loaiGhe, String tenXe, int tinhTrang) {
        this.gID = gID;
        this.tenGhe = tenGhe;
        this.loaiGhe = loaiGhe;
        this.tenXe = tenXe;
        this.tinhTrang = tinhTrang;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getgID() {
        return gID;
    }

    public void setgID(int gID) {
        this.gID = gID;
    }

    public String getTenGhe() {
        return tenGhe;
    }

    public void setTenGhe(String tenGhe) {
        this.tenGhe = tenGhe;
    }

    public String getLoaiGhe() {
        return loaiGhe;
    }

    public void setLoaiGhe(String loaiGhe) {
        this.loaiGhe = loaiGhe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public Ghe(String tenGhe, String loaiGhe, String tenXe, int tinhTrang) {
        this.tenGhe = tenGhe;
        this.loaiGhe = loaiGhe;
        this.tenXe = tenXe;
        this.tinhTrang = tinhTrang;
    }
}
