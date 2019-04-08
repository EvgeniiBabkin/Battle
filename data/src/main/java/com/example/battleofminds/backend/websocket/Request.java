package com.example.battleofminds.backend.websocket;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Request {

    private transient String method;

    private transient String receiveAt;

    @SerializedName("token")
    private String token;

    @SerializedName("entity")
    private Object entity;

    public Request(@NonNull final String method,
                   @NonNull final String receiveAt,
                   @NonNull final String token,
                   @NonNull final Object entity) {
        this.method = method;
        this.receiveAt = receiveAt;
        this.token = token;
        this.entity = entity;
    }

    @NonNull
    public String toString(@NonNull final Gson gson) {
        return gson.toJson(this);
    }

    public String getMethod() {
        return method;
    }

    public String getReceiveAt() {
        return receiveAt;
    }
}
