package com.example.battleofminds.view.fragment;

import android.support.v4.app.Fragment;

import com.example.battleofminds.internal.di.HasComponent;

public abstract class BaseFragment extends Fragment {


    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
