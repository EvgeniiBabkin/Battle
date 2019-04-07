package com.example.battleofminds.network;

import com.example.battleofminds.model.pojo.CredentialsObject;
import com.example.battleofminds.model.pojo.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BomApi {

    @GET("registration/signin")
    Observable<String> signIn(@Header("Authorization") String credentials);

    @GET("main")
    Observable<User> getUser();

    @POST("registration/signup")
    Observable<String> signUp(@Body CredentialsObject credentials);

}
