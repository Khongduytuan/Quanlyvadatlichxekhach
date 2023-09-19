package com.example.myapplication.model;

import java.io.Serializable;

public class Xe implements Serializable {
    private int xID;
    private String tenXe, bienSo;
    private int soGhe;


    public Xe() {
    }

    public Xe(int xID, String tenXe, String bienSo, int soGhe) {
        this.xID = xID;
        this.tenXe = tenXe;
        this.bienSo = bienSo;
        this.soGhe = soGhe;
    }

    public Xe(String tenXe, String bienSo, int soGhe) {
        this.tenXe = tenXe;
        this.bienSo = bienSo;
        this.soGhe = soGhe;
    }

    public int getxID() {
        return xID;
    }

    public void setxID(int xID) {
        this.xID = xID;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }
}
