package com.example.battleofminds.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.battleofminds.R;
import com.example.battleofminds.view.activity.MainActivity;
import com.example.battleofminds.constants.AppConstants;

public class HomeFragment extends Fragment {


    private MainActivity mainActivity;
    private MenuItem currentGame, settings;

    private OnFragmentChangedListener mListner;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentChangedListener)
            mListner = (OnFragmentChangedListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ActionBar actionBar = mainActivity.getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(false);
        mListner.onFragmentChanged(AppConstants.HOME_FRAGMENT);
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home_fragment, menu);
        currentGame = menu.findItem(R.id.current_game);
        settings = menu.findItem(R.id.settings);
        startMode();

    }

    private void startMode(){
        currentGame.setVisible(true);
        settings.setVisible(true);

    }
}
