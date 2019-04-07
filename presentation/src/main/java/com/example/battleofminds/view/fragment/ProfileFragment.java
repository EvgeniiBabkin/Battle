package com.example.battleofminds.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.battleofminds.R;
import com.example.battleofminds.constants.AppConstants;
import com.example.battleofminds.internal.di.components.MainComponent;
import com.example.battleofminds.presenter.ProfilePresenter;
import com.example.battleofminds.view.ProfileView;
import com.example.battleofminds.view.activity.MainActivity;

import javax.inject.Inject;

public class ProfileFragment extends BaseFragment implements ProfileView {


    @Inject
    ProfilePresenter presenter;

    private MainActivity mainActivity;

    private TextView tvLogin, tvScore;

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
        mainActivity = (MainActivity) getActivity();
        getComponent(MainComponent.class).inject(this);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, null);
        ActionBar actionBar = mainActivity.getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(true);
        mListner.onFragmentChanged(AppConstants.PROFILE_FRAGMENT);

        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvLogin = view.findViewById(R.id.user_name_text_view);
        tvScore = view.findViewById(R.id.user_score_text_view);

        presenter.setView(this);
        presenter.trackData();

    }

    @Override
    public void setAvatar(String avatarUrl) {

    }

    @Override
    public void setLogin(String login) {
        tvLogin.setText(String.format(getString(R.string.login_s), login));
    }

    @Override
    public void setScore(Integer score) {
        tvScore.setText(String.format(getString(R.string.score_s), score.toString()));
    }
}
