package com.example.myapplication.api;

import com.example.myapplication.model.User;
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

public interface ApiServiceUser {
//    public String URL_HEADER_GET_DATA_USER = "http://192.168.1.25/testconnect/";
    public String URL_HEADER_GET_DATA_USER = "http://192.168.43.70/testconnect/";
    public String URL_BODY_LOGIN = "login.php";
    public String URL_BODY_INSERT_DATA_USER = "insertDataUser.php";
    public String URL_BODY_DELETE_DATA_USER = "deleteDataUser.php";
    public String URL_BODY_UPDATE_DATA_USER = "updateDataUser.php";
    public String URL_BODY_GET_DATA_USER = "getDataUser.php";
    public String URL_BODY_GET_DATA_BY_ROLE_USER = "getDataUserByRole.php";

    Gson gson = new GsonBuilder().setLenient().create();
    ApiServiceUser apiServiceUser = new Retrofit.Builder()
            .baseUrl(URL_HEADER_GET_DATA_USER)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServiceUser.class);

    @GET(URL_BODY_GET_DATA_USER)
    Call<List<User>> getDataUser();

    @POST(URL_BODY_GET_DATA_BY_ROLE_USER)
    @FormUrlEncoded
    Call<List<User>> getDataUserByRole( @Field("role") String role);

    @POST(URL_BODY_DELETE_DATA_USER)
    @FormUrlEncoded
    Call<String> deleteUser(@Field("uID") int uID);

    @POST(URL_BODY_UPDATE_DATA_USER)
    @FormUrlEncoded
    Call<String> updateUser(@Field("uID") int uID,
                            @Field("hoTen") String hoTen,
                            @Field("ngayThangNamSinh") String ngayThangNamSinh,
                            @Field("diaChi") String diaChi,
                            @Field("sdt") String sdt,
                            @Field("email") String email);

    @POST(URL_BODY_LOGIN)
    @FormUrlEncoded
    Call<List<User>> loginUser(@Field("userName") String userName,
                               @Field("passWord") String passWord);

    @POST(URL_BODY_INSERT_DATA_USER)
    @FormUrlEncoded
    Call<String> dangKyAcc(@Field("hoTen") String hoTen,
                           @Field("ngayThangNamSinh") String ngayThangNamSinh,
                           @Field("diaChi") String diaChi,
                           @Field("sdt") String sdt,
                           @Field("email") String email,
                           @Field("userName") String userName,
                           @Field("passWord") String passWord,
                           @Field("role") String role);
}
