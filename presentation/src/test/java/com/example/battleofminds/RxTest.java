package com.example.battleofminds;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.reactivex.Observable;

public class RxTest {


    public static void main(String[] args) {

        Observable.fromCallable(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("your name:");
            return reader.readLine();
        })
                .subscribe(System.out::println,
                System.out::println,
                () -> System.out.println("finish"));
    }
}
