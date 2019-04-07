package com.example.battleofminds.repository.user;

import com.example.battleofminds.object.User;
import com.example.battleofminds.repository.UserRepo;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class UserRepoImpl implements UserRepo {


    @Inject
    UserRepoImpl(){}

    @Override
    public Flowable<User> getUser() {
        return Flowable.just(new User());
    }
}
