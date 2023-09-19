package com.example.myapplication.api;

import com.example.myapplication.model.Ghe;
import com.example.myapplication.model.Xe;
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

public interface ApiServiceGhe {

//    public String URL_HEADER_GET_DATA_GHE = "http://192.168.1.25/testconnect/";
    public String URL_HEADER_GET_DATA_GHE = "http://192.168.43.70/testconnect/";
    public String URL_BODY_GET_DATA_GHE = "getDataGhe.php";
    public String URL_BODY_INSERT_DATA_GHE = "insertDataGhe.php";
    public String URL_BODY_DELETE_DATA_GHE = "deleteDataGhe.php";
    public String URL_BODY_UPDATE_DATA_GHE = "updateDataGhe.php";

    Gson gson = new GsonBuilder().setLenient().create();
    ApiServiceGhe apiServiceGhe = new Retrofit.Builder()
            .baseUrl(URL_HEADER_GET_DATA_GHE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServiceGhe.class);

    @GET(URL_BODY_GET_DATA_GHE)
    Call<List<Ghe>> getDataGhe();

    @POST(URL_BODY_UPDATE_DATA_GHE)
    @FormUrlEncoded
    Call<String> updateDataGhe(@Field("gID") int gID,
                               @Field("tenGhe") String tenGhe,
                               @Field("loaiGhe") String loaiGhe,
                               @Field("tinhTrang") Boolean tinhTrang,
                               @Field("xID") int xID);

    @POST(URL_BODY_INSERT_DATA_GHE)
    @FormUrlEncoded
    Call<String> insertDataGhe(@Field("tenGhe") String tenGhe,
                               @Field("loaiGhe") String loaiGhe,
                               @Field("tinhTrang") Boolean tinhTrang,
                               @Field("xID") int xID);

    @POST(URL_BODY_DELETE_DATA_GHE)
    @FormUrlEncoded
    Call<String> deleteDataGhe(@Field("gID") int gID);


}