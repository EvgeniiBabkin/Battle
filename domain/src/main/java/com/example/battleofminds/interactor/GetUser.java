package com.example.battleofminds.interactor;


import com.example.battleofminds.executor.PostExecutionThread;
import com.example.battleofminds.executor.ThreadExecutor;
import com.example.battleofminds.object.User;
import com.example.battleofminds.repository.UserRepo;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetUser extends UseCase<User, Void> {

    private final UserRepo userRepo;

    @Inject
    GetUser(UserRepo userRepo, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepo = userRepo;
    }

    @Override
    Observable<User> buildUseCaseObservable(Void aVoid) {
        return userRepo.getUser().toObservable();
    }
}
