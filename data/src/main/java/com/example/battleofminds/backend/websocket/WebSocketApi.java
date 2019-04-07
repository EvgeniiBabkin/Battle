package com.example.battleofminds.backend.websocket;

import com.google.gson.Gson;

import javax.inject.Singleton;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

@Singleton
public class WebSocketApi {


    public WebSocketApi() {

    }

    private final String TOPIC_PREFIX = "/mobile%s";
    private final String DESTINATION_PREFIX = "/server%s";

    private final StompClient client = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://10.0.2.2:8080/websocket");
    private final Gson gson = new Gson();





}
