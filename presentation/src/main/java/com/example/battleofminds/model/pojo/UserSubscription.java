package com.example.battleofminds.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSubscription {
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("points")
    @Expose
    private Integer points;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
