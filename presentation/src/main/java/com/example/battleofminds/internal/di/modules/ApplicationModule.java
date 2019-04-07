package com.example.battleofminds.internal.di.modules;

import android.content.Context;

import com.example.battleofminds.executor.JobExecutor;
import com.example.battleofminds.executor.PostExecutionThread;
import com.example.battleofminds.executor.ThreadExecutor;
import com.example.battleofminds.repository.UserRepo;
import com.example.battleofminds.repository.user.UserRepoImpl;
import com.example.battleofminds.run.AndroidApplication;
import com.example.battleofminds.run.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    UserRepo provideUserRepo (UserRepoImpl userRepo){
        return userRepo;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

}
