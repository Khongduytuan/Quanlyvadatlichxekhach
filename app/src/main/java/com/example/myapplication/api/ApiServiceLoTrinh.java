package com.example.myapplication.api;

import com.example.myapplication.model.LoTrinh;
import com.example.myapplication.model.Xe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServiceLoTrinh {
//    public String URL_HEADER_GET_DATA_LO_TRINH = "http://192.168.1.25/testconnect/";
    public String URL_HEADER_GET_DATA_LO_TRINH = "http://192.168.43.70/testconnect/";
    public String URL_BODY_GET_DATA_LO_TRINH = "getDataLoTrinh.php";
    public String URL_BODY_INSERT_DATA_LO_TRINH = "insertDataLoTrinh.php";
    public String URL_BODY_DELETE_DATA_LO_TRINH = "deleteDataLoTrinh.php";
    public String URL_BODY_UPDATE_DATA_LO_TRINH = "updateDataLoTrinh.php";

    Gson gson = new GsonBuilder().setLenient().create();

    ApiServiceLoTrinh apiServiceLoTrinh = new Retrofit.Builder()
            .baseUrl(URL_HEADER_GET_DATA_LO_TRINH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServiceLoTrinh.class);

    @GET(URL_BODY_GET_DATA_LO_TRINH)
    Call<List<LoTrinh>> getDataLoTrinh();

    @POST(URL_BODY_UPDATE_DATA_LO_TRINH)
    @FormUrlEncoded
    Call<String> updateDataLoTrinh(@Field("ltID") int ltID,
                                   @Field("diemDau") String diemDau,
                                   @Field("diemCuoi") String diemCuoi,
                                   @Field("thoiGianXuatPhat") java.util.Date thoiGianXuatPhat,
                                   @Field("thoiGianToiNoi") java.util.Date thoiGianToiNoi,
                                   @Field("xID") int xID);

    @POST(URL_BODY_UPDATE_DATA_LO_TRINH)
    @FormUrlEncoded
    Call<String> updateDataLoTrinh2(@Field("ltID") int ltID,
                                   @Field("diemDau") String diemDau,
                                   @Field("diemCuoi") String diemCuoi,
                                   @Field("thoiGianXuatPhat") String thoiGianXuatPhat,
                                   @Field("thoiGianToiNoi") String thoiGianToiNoi,
                                   @Field("xID") int xID);

    @POST(URL_BODY_DELETE_DATA_LO_TRINH)
    @FormUrlEncoded
    Call<String> deleteDataLoTrinh(@Field("ltID") int ltID);

    @POST(URL_BODY_INSERT_DATA_LO_TRINH)
    @FormUrlEncoded
    Call<String> insertDataLoTrinh(@Field("diemDau") String diemDau,
                                    @Field("diemCuoi") String diemCuoi,
                                    @Field("thoiGianXuatPhat") String thoiGianXuatPhat,
                                    @Field("thoiGianToiNoi") String thoiGianToiNoi,
                                    @Field("xID") int xID);

}
