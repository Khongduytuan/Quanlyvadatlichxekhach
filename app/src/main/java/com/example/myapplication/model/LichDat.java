package com.example.myapplication.model;

import java.io.Serializable;

public class LichDat implements Serializable {
    private int ldID;
    private String thoiGianDat;
    private int tinhTrangXacNhan;
    private String diemDau, diemCuoi, thoiGianXuatPhat, thoiGianToiNoi, tenXe, hoTen;


    public LichDat(int ldID,
                   String thoiGianDat,
                   int tinhTrangXacNhan,
                   String diemDau,
                   String diemCuoi,
                   String thoiGianXuatPhat,
                   String thoiGianToiNoi,
                   String tenXe,
                   String hoTen) {
        this.ldID = ldID;
        this.thoiGianDat = thoiGianDat;
        this.tinhTrangXacNhan = tinhTrangXacNhan;
        this.diemDau = diemDau;
        this.diemCuoi = diemCuoi;
        this.thoiGianXuatPhat = thoiGianXuatPhat;
        this.thoiGianToiNoi = thoiGianToiNoi;
        this.tenXe = tenXe;
        this.hoTen = hoTen;
    }

    public LichDat() {
    }

    public int getLdID() {
        return ldID;
    }

    public void setLdID(int ldID) {
        this.ldID = ldID;
    }

    public String getThoiGianToiNoi() {
        return thoiGianToiNoi;
    }

    public void setThoiGianToiNoi(String thoiGianToiNoi) {
        this.thoiGianToiNoi = thoiGianToiNoi;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getThoiGianDat() {
        return thoiGianDat;
    }

    public void setThoiGianDat(String thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }

    public int getTinhTrangXacNhan() {
        return tinhTrangXacNhan;
    }

    public void setTinhTrangXacNhan(int tinhTrangXacNhan) {
        this.tinhTrangXacNhan = tinhTrangXacNhan;
    }

    public String getDiemDau() {
        return diemDau;
    }

    public void setDiemDau(String diemDau) {
        this.diemDau = diemDau;
    }

    public String getDiemCuoi() {
        return diemCuoi;
    }

    public void setDiemCuoi(String diemCuoi) {
        this.diemCuoi = diemCuoi;
    }

    public String getThoiGianXuatPhat() {
        return thoiGianXuatPhat;
    }

    public void setThoiGianXuatPhat(String thoiGianXuatPhat) {
        this.thoiGianXuatPhat = thoiGianXuatPhat;
    }
}
