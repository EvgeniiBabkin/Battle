package com.example.battleofminds.internal.di.modules;

import android.app.Activity;

import com.example.battleofminds.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity mActivity){
        this.mActivity = mActivity;
    }

    @Provides
    @PerActivity
    Activity activity(){
        return this.mActivity;
    }
}
