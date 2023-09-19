package com.example.myapplication.api;

import com.example.myapplication.model.LichDat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServiceLichDat {
//    public String URL_HEADER_GET_DATA_LICH_DAT = "http://192.168.1.25/testconnect/";
    public String URL_HEADER_GET_DATA_LICH_DAT = "http://192.168.43.70/testconnect/";
    public String URL_BODY_INSERT_DATA_LICH_DAT = "insertDataLichDat.php";
    public String URL_BODY_GET_DATA_LICH_DAT_BY_IUD = "getDataLichDatByuID.php";
    public String URL_BODY_GET_DATA_LICH_DAT_FULL_FOR_ADMIN = "getDataLichDatFullForAdmin.php";
    public String URL_BODY_CONFIRM_LICH_DAT_FOR_ADMIN = "updateDataLichDat.php";
    public String URL_BODY_DELETE_DATA_LICH_DAT = "deleteDataLichDat.php";

    Gson gson = new GsonBuilder().setLenient().create();
    ApiServiceLichDat apiServiceLichDat = new Retrofit.Builder()
            .baseUrl(URL_HEADER_GET_DATA_LICH_DAT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServiceLichDat.class);

    @POST(URL_BODY_INSERT_DATA_LICH_DAT)
    @FormUrlEncoded
    Call<String> insertDataLichDat(@Field("ltID") int ltID,
                                   @Field("uID") int uID,
                                   @Field("thoiGianDat") String thoiGianDat,
                                   @Field("tinhTrangXacNhan") int tinhTrangXacNhan);

    @POST(URL_BODY_GET_DATA_LICH_DAT_BY_IUD)
    @FormUrlEncoded
    Call<List<LichDat>> getDataLichDatClient(@Field("uID") int uID);

    @GET(URL_BODY_GET_DATA_LICH_DAT_FULL_FOR_ADMIN)
    Call<List<LichDat>> getDataLichDatFullForAdmin();

    @POST(URL_BODY_CONFIRM_LICH_DAT_FOR_ADMIN)
    @FormUrlEncoded
    Call<String> confirmLichDatForAdmin(@Field("ldID") int ldID,
                                        @Field("tinhTrangXacNhan") String tinhTrangXacNhan);

    @POST(URL_BODY_DELETE_DATA_LICH_DAT)
    @FormUrlEncoded
    Call<String> deleteDataLichDat(@Field("ldID") int ldID);

}
