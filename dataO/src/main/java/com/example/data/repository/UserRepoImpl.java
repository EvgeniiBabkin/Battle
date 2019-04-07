package com.example.battleofminds.repository;

import com.example.battleofminds.object.User;
import com.example.battleofminds.repository.UserRepo;
import com.example.battleofminds.backend.websocket.WebSocketApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class UserRepoImpl implements UserRepo {


    private final WebSocketApi webSocketApi;

    @Inject
    public UserRepoImpl(final WebSocketApi webSocketApi){
        this.webSocketApi = webSocketApi;
    }

    @Override
    public Flowable<User> getUser() {
        return null;
    }
}
