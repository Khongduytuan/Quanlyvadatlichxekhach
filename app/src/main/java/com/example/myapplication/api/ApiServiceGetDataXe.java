package com.example.myapplication.api;

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
import retrofit2.http.Query;

public interface ApiServiceGetDataXe {
//    public String URL_HEADER_GET_DATA_XE = "http://192.168.1.25/testconnect/";
    public String URL_HEADER_GET_DATA_XE = "http://192.168.43.70/testconnect/";
    public String URL_BODY_GET_DATA_XE = "getDataXe.php";
    public String URL_BODY_DELETE_DATA_XE = "deleteDataXe.php";
    public String URL_BODY_UPDATE_DATA_XE = "updateDataXe.php";
    public String URL_BODY_INSERT_DATA_XE = "insertDataXe.php";
    public String URL_BODY_GET_TEN_XE_BY_XID_DATA_XE = "getDataTenXeByxID.php";


    Gson gson = new GsonBuilder().setLenient().create();
    ApiServiceGetDataXe apiServiceGetDataXe = new Retrofit.Builder()
            .baseUrl(URL_HEADER_GET_DATA_XE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServiceGetDataXe.class);


    @GET(URL_BODY_GET_DATA_XE)
    Call<List<Xe>> getDataXe();

    @GET(URL_BODY_GET_TEN_XE_BY_XID_DATA_XE)
    Call<String> getTenXeByxID(@Query("tenXe") String tenXe);

    @POST(URL_BODY_DELETE_DATA_XE)
    @FormUrlEncoded
    Call<String> deleteDataXe(@Field("xID") int xID);

    @POST(URL_BODY_UPDATE_DATA_XE)
    @FormUrlEncoded
    Call<String> updateDataXe(@Field("xID") int xID,
                              @Field("tenXe") String tenXe,
                              @Field("bienSo") String bienSo,
                              @Field("soGhe") int soGhe);

    @POST(URL_BODY_INSERT_DATA_XE)
    @FormUrlEncoded
    Call<String> insertDataXe(@Field("tenXe") String tenXe,
                              @Field("bienSo") String bienSo,
                              @Field("soGhe") int soGhe);
}
