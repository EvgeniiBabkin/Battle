package com.example.battleofminds.run;


import android.app.Application;

import com.example.battleofminds.internal.di.components.ApplicationComponent;
import com.example.battleofminds.internal.di.components.DaggerApplicationComponent;
import com.example.battleofminds.internal.di.modules.ApplicationModule;

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
