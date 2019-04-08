package com.example.battleofminds.repository;

import com.example.battleofminds.object.CredentialObject;

import io.reactivex.Completable;

public interface AuthRepo {

    Completable signIn(CredentialObject credential);
}
