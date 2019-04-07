package com.example.battleofminds.network;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService mInstance;

    private static final String BASE_URL = "http://192.168.0.10:8080/";
    private Retrofit mRetrofit;


    private NetworkService(String userName, String password){


        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder().header("Authorization", Credentials.basic(userName, password));
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();


        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public static NetworkService getInstance(String userName, String password){

        if (mInstance == null){
            mInstance = new NetworkService(userName, password);
        }

        return mInstance;

    }

    public BomApi getBomApi(){
        return mRetrofit.create(BomApi.class);
    }
}
