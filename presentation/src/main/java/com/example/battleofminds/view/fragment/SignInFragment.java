package com.example.battleofminds.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.battleofminds.R;
import com.example.battleofminds.view.SignInView;

public class SignInFragment extends BaseFragment implements SignInView {

    private EditText etLogin, etPassword;
    private Button bSignIn;
    private TextView bSignUp;

    private ProgressBar mProgressBar;
    private TextView mPleaseWait;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        bindView(view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etLogin = view.findViewById(R.id.et_login);
        etPassword = view.findViewById(R.id.et_password);
        bSignIn = view.findViewById(R.id.b_enter);
        bSignUp = view.findViewById(R.id.tv_registration);
        init();
    }

    private void init() {
        bSignIn.setOnClickListener(v -> );
    }

    private void bindView(View view){


    }

    @Override
    public void goToHome() {

    }

    @Override
    public void showLoginIsIncorrect() {

    }

    @Override
    public void showPasswordIsIncorrect() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
