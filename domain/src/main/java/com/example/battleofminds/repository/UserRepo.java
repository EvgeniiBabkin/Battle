package com.example.battleofminds.repository;

import com.example.battleofminds.object.User;

import io.reactivex.Flowable;

public interface UserRepo {

    Flowable<User> getUser();
}
