package com.example.battleofminds.internal.di.components;

import android.app.Activity;

import com.example.battleofminds.internal.di.PerActivity;
import com.example.battleofminds.internal.di.modules.ActivityModule;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
@PerActivity
public interface ActivityComponent {

    Activity activity();
}
