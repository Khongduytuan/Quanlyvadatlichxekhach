package com.example.myapplication.model;

import java.io.Serializable;
import java.sql.Date;

public class LoTrinh implements Serializable {
    private int ltID;
    private String diemDau, diemCuoi;
    private String thoiGianXuatPhat, thoiGianToiNoi;
    private String tenXe;

    public LoTrinh() {
    }

    public LoTrinh(int ltID, String diemDau, String diemCuoi, String thoiGianXuatPhat, String thoiGianToiNoi, String tenXe) {
        this.ltID = ltID;
        this.diemDau = diemDau;
        this.diemCuoi = diemCuoi;
        this.thoiGianXuatPhat = thoiGianXuatPhat;
        this.thoiGianToiNoi = thoiGianToiNoi;
        this.tenXe = tenXe;
    }

    public LoTrinh(String diemDau, String diemCuoi, String thoiGianXuatPhat, String thoiGianToiNoi, String tenXe) {
        this.diemDau = diemDau;
        this.diemCuoi = diemCuoi;
        this.thoiGianXuatPhat = thoiGianXuatPhat;
        this.thoiGianToiNoi = thoiGianToiNoi;
        this.tenXe = tenXe;
    }

    public int getLtID() {
        return ltID;
    }

    public void setLtID(int ltID) {
        this.ltID = ltID;
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
}
