package com.example.battleofminds.backend.websocket;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

public class Response {


    @SerializedName("entity")
    private JsonElement entity;

    @NonNull
    public <T> T getEntity(@NonNull final Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(entity, clazz);
    }
}
