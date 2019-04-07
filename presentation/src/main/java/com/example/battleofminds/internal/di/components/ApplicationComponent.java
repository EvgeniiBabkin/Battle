package com.example.battleofminds.internal.di.components;

import android.content.Context;

import com.example.battleofminds.executor.PostExecutionThread;
import com.example.battleofminds.executor.ThreadExecutor;
import com.example.battleofminds.internal.di.modules.ApplicationModule;
import com.example.battleofminds.repository.UserRepo;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {


    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    UserRepo userRepo();

}
