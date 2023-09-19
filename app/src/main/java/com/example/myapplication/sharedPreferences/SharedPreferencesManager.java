package com.example.myapplication.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.model.User;

public class SharedPreferencesManager {
    private static SharedPreferencesManager instance;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "DataUser";

    private SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager(context);
        }
        return instance;
    }

    public void saveUserData(User userInfor) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("uID", String.valueOf(userInfor.getuID()));
        editor.putString("hoTen", userInfor.getHoTen());
        editor.putString("ngayThangNamSinh", userInfor.getNgayThangNamSinh());
        editor.putString("diaChi", userInfor.getDiaChi());
        editor.putString("sdt", userInfor.getSdt());
        editor.putString("email", userInfor.getEmail());
        editor.apply();
    }

    public String sharedPreferencesGetHoTen() {
        return sharedPreferences.getString("hoTen", "");
    }
    public String sharedPreferencesGetUID() {
        return sharedPreferences.getString("uID", "");
    }
    public String sharedPreferencesGetNgayThangNamSinh() {
        return sharedPreferences.getString("ngayThangNamSinh", "");
    }
    public String sharedPreferencesGetDiaChi() {
        return sharedPreferences.getString("diaChi", "");
    }
    public String sharedPreferencesGetsdt() {
        return sharedPreferences.getString("sdt", "");
    }

    public String sharedPreferencesGetemail() {
        return sharedPreferences.getString("email", "");
    }






}
