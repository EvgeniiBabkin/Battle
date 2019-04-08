package com.example.battleofminds.interactor;


import com.example.battleofminds.executor.PostExecutionThread;
import com.example.battleofminds.executor.ThreadExecutor;
import com.example.battleofminds.object.CredentialObject;
import com.example.battleofminds.repository.AuthRepo;

import io.reactivex.Observable;

public class ToSignIn extends UseCase<String, CredentialObject> {

    private AuthRepo authRepo;

    ToSignIn(AuthRepo authRepo, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        super(threadExecutor, postExecutionThread);
        this.authRepo = authRepo;
    }

    @Override
    Observable<String> buildUseCaseObservable(CredentialObject credentialObject) {
        return authRepo.signIn(credentialObject).toObservable();
    }
}
