package com.example.battleofminds.view.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.battleofminds.R;
import com.example.battleofminds.constants.AppConstants;
import com.example.battleofminds.internal.di.components.ApplicationComponent;
import com.example.battleofminds.internal.di.modules.ActivityModule;
import com.example.battleofminds.run.AndroidApplication;
import com.example.battleofminds.utilites.DialogUtilities;

public class BaseActivity extends AppCompatActivity implements DialogUtilities.OnCompleteListener {

    public static final String BASE_URL = "http://192.168.0.10:8080/";

    private SharedPreferences sPref;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public void saveShared(String login, String password) {
        sPref = getSharedPreferences("LoginPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("Login", login);
        editor.putString("Password", password);
        editor.commit();
    }

    public boolean isStringNull(String string){

        if(string.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    public void tapPromtToExit(Activity activity) {
        this.activity = activity;
        FragmentManager manager = getSupportFragmentManager();
        DialogUtilities dialog = DialogUtilities.newInstance(getString(R.string.exit), getString(R.string.exit_text), getString(R.string.yes), getString(R.string.no), AppConstants.BUNDLE_EXIT);
        dialog.show(manager, AppConstants.BUNDLE_KEY_DIALOG_FRAGMENT);
    }

    @Override
    public void onComplete(Boolean isOkPressed, String viewIdText) {

        if (isOkPressed) {
            if (viewIdText.equals(AppConstants.BUNDLE_EXIT)) {
                activity.finish();
            }
        }
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }


    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
