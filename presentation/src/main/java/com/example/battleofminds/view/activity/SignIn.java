package com.example.battleofminds.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.battleofminds.R;
import com.example.battleofminds.constants.AppConstants;
import com.example.battleofminds.network.BomApi;
import com.example.battleofminds.utilites.ActivityUtilites;
import com.example.battleofminds.utilites.DialogUtilities;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Credentials;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignIn extends BaseActivity implements DialogUtilities.OnCompleteListener {


    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Context mContext;

    private EditText etLogin, etPassword;
    private Button bSignIn;
    private TextView bSignUp;

    private ProgressBar mProgressBar;
    private TextView mPleaseWait;

    private Retrofit mRetrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mContext = SignIn.this;

        mProgressBar = findViewById(R.id.progressBar);
        mPleaseWait = findViewById(R.id.pleaseWait);

        mPleaseWait.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);

        etLogin = findViewById(R.id.et_login);
        etPassword = findViewById(R.id.et_password);

        bSignIn = findViewById(R.id.b_enter);

        bSignUp = findViewById(R.id.tv_registration);

        init();

    }

    private void init(){

        bSignUp.setOnClickListener(v -> {
            ActivityUtilites.getInstance().invokeNewActivity(SignIn.this, SignUp.class, false);
        });

        bSignIn.setOnClickListener(v -> {

            // Test mode
            ActivityUtilites.getInstance().invokeNewActivity(SignIn.this, MainActivity.class, true);

            //

            String login = etLogin.getText().toString();
            String password = etPassword.getText().toString();

            if (isStringNull(login) && isStringNull(password)){
                Toast.makeText(mContext, "You must fill out all the fields", Toast.LENGTH_SHORT).show();
            }else{
            compositeDisposable.add(signIn(etLogin.getText().toString(), etPassword.getText().toString()).
                    subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(pb -> {
                        mPleaseWait.setVisibility(View.VISIBLE);
                        mProgressBar.setVisibility(View.VISIBLE);
                    })
                    .subscribe(
                            res -> {
                                mPleaseWait.setVisibility(View.GONE);
                                mProgressBar.setVisibility(View.GONE);
                                if (res.equals("success")){
                                    saveShared(etLogin.getText().toString(), etPassword.getText().toString());
                                    ActivityUtilites.getInstance().invokeNewActivity(SignIn.this, MainActivity.class, true);
                                }
                                else if (res.equals("error")){
                                    Toast.makeText(this, "error success", Toast.LENGTH_SHORT).show();
                                }
                            },
                            err -> {
                                mPleaseWait.setVisibility(View.GONE);
                                mProgressBar.setVisibility(View.GONE);
                                FragmentManager manager = getSupportFragmentManager();
                                DialogUtilities dialog = DialogUtilities.newInstance(getString(R.string.error), getString(R.string.no_network), getString(R.string.ok), null, AppConstants.BUNDLE_NO_NETWORK);
                                dialog.show(manager, AppConstants.BUNDLE_KEY_DIALOG_FRAGMENT);
                            }
                    )
            );}

        });
    }


    private Observable<String> signIn(String userName, String password){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return mRetrofit.create(BomApi.class)
                .signIn(Credentials.basic(userName, password));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        tapPromtToExit(this);
    }

}
