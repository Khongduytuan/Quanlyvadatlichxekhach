package com.example.myapplication.model;

import java.io.Serializable;

public class User implements Serializable {
    private int uID;
    private String hoTen, ngayThangNamSinh, diaChi, sdt, email, userName, passWord, role;


    public User(int uID, String hoTen, String ngayThangNamSinh,
                String diaChi, String sdt, String email, String userName, String passWord, String role) {
        this.uID = uID;
        this.hoTen = hoTen;
        this.ngayThangNamSinh = ngayThangNamSinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public User(String hoTen, String ngayThangNamSinh, String diaChi,
                String sdt, String email, String userName, String passWord, String role) {
        this.hoTen = hoTen;
        this.ngayThangNamSinh = ngayThangNamSinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public User() {
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public String getNgayThangNamSinh() {
        return ngayThangNamSinh;
    }

    public void setNgayThangNamSinh(String ngayThangNamSinh) {
        this.ngayThangNamSinh = ngayThangNamSinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
