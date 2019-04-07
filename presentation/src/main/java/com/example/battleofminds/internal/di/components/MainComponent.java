package com.example.battleofminds.internal.di.components;

import com.example.battleofminds.internal.di.PerActivity;
import com.example.battleofminds.internal.di.modules.ActivityModule;
import com.example.battleofminds.internal.di.modules.MainModule;
import com.example.battleofminds.view.fragment.ProfileFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MainModule.class})
public interface MainComponent {

    void inject(ProfileFragment profileFragment);
}
