package com.example.battleofminds.view.activity;

import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.battleofminds.R;
import com.example.battleofminds.utilites.ActivityUtilites;

public class SplashActivity extends AppCompatActivity {

    private ImageView startImageView;
    private ProgressBar progressBar;
    private Animation animation;
    private ConstraintLayout layout;

    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        startImageView = findViewById(R.id.iv_splash_icon);
        progressBar = findViewById(R.id.progressBar);
        layout = findViewById(R.id.splash_layout);

        animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
    }

    private void initFunctionality(){

        layout.postDelayed(new Runnable() {
            @Override
            public void run() {
                startImageView.setAnimation(animation);
                progressBar.setVisibility(View.GONE);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        sPref = getSharedPreferences("LoginPref", MODE_PRIVATE);
                        String login = sPref.getString("Login", "");
                        String password = sPref.getString("Password", "");
                        if ((login.length() & password.length()) != 0){
                            ActivityUtilites.getInstance().invokeNewActivity(SplashActivity.this, MainActivity.class, true);
                        }
                        else {
                            ActivityUtilites.getInstance().invokeNewActivity(SplashActivity.this, SignIn.class, true);
                        }


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initFunctionality();
    }
}
