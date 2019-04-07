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
import com.example.battleofminds.model.pojo.CredentialsObject;
import com.example.battleofminds.network.BomApi;
import com.example.battleofminds.utilites.ActivityUtilites;
import com.example.battleofminds.utilites.DialogUtilities;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends BaseActivity {


    private Context mContext;

    private EditText userName, userPassword, email;
    private Button signUp;

    private ProgressBar mProgressBar;
    private TextView mPleaseWait;

    private Retrofit mRetrofit;


    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private CredentialsObject credentialsObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mContext = SignUp.this;

        mProgressBar = findViewById(R.id.progressBar);
        mPleaseWait = findViewById(R.id.loadingPleaseWait);

        mPleaseWait.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);

        userName = findViewById(R.id.et_userName);
        userPassword = findViewById(R.id.et_userPassword);
        email = findViewById(R.id.et_userEmail);

        signUp = findViewById(R.id.b_signUp);

        signUp.setOnClickListener(v -> {

            String login = userName.getText().toString();
            String password = userPassword.getText().toString();

            if (isStringNull(login) && isStringNull(password)){
                Toast.makeText(mContext, "You must fill out all the fields", Toast.LENGTH_SHORT).show();
            }else {
            credentialsObject = new CredentialsObject();
            credentialsObject.setLogin(userName.getText().toString());
            credentialsObject.setPassword(userPassword.getText().toString());

            compositeDisposable.add(signUp().
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

                                if (res.equals("registration")){
                                    saveShared(credentialsObject.getLogin(), credentialsObject.getPassword());
                                    ActivityUtilites.getInstance().invokeNewActivity(SignUp.this, MainActivity.class, true);
                                }
                                else if (res.equals("error")){
                                    FragmentManager manager = getSupportFragmentManager();
                                    DialogUtilities dialog = DialogUtilities.newInstance(getString(R.string.busy_name), getString(R.string.other_name), getString(R.string.yes), getString(R.string.no), AppConstants.BUNDLE_BUSY_NAME);
                                    dialog.show(manager, AppConstants.BUNDLE_KEY_DIALOG_FRAGMENT);
                                    Toast.makeText(this, "error name", Toast.LENGTH_SHORT).show();
                                }
                            },
                            err -> {
                                mPleaseWait.setVisibility(View.GONE);
                                mProgressBar.setVisibility(View.GONE);
                                Toast.makeText(this, "error net seti", Toast.LENGTH_SHORT).show();
                            }
                    )
            );}
        });
    }

    private Observable<String> signUp(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return mRetrofit.create(BomApi.class)
                .signUp(credentialsObject);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }


}
