package com.example.battleofminds.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("user_subscribers")
    @Expose
    private List<Object> userSubscribers = null;
    @SerializedName("user_subscriptions")
    @Expose
    private List<UserSubscription> userSubscriptions = null;
    @SerializedName("user_dialogs")
    @Expose
    private List<UserDialog> userDialogs = null;

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

    public List<Object> getUserSubscribers() {
        return userSubscribers;
    }

    public void setUserSubscribers(List<Object> userSubscribers) {
        this.userSubscribers = userSubscribers;
    }

    public List<UserSubscription> getUserSubscriptions() {
        return userSubscriptions;
    }

    public void setUserSubscriptions(List<UserSubscription> userSubscriptions) {
        this.userSubscriptions = userSubscriptions;
    }

    public List<UserDialog> getUserDialogs() {
        return userDialogs;
    }

    public void setUserDialogs(List<UserDialog> userDialogs) {
        this.userDialogs = userDialogs;
    }
}
