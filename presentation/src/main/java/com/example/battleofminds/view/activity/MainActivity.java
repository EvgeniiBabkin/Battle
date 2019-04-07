package com.example.battleofminds.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.battleofminds.R;
import com.example.battleofminds.constants.AppConstants;
import com.example.battleofminds.internal.di.HasComponent;
import com.example.battleofminds.internal.di.components.DaggerMainComponent;
import com.example.battleofminds.internal.di.components.MainComponent;
import com.example.battleofminds.network.NetworkService;
import com.example.battleofminds.view.fragment.HomeFragment;
import com.example.battleofminds.view.fragment.OnFragmentChangedListener;
import com.example.battleofminds.view.fragment.ProfileFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements OnFragmentChangedListener, HasComponent<MainComponent> {

    private HomeFragment homeFragment = new HomeFragment();
    private ProfileFragment profileFragment;
    private Toolbar toolbar;
    private Fragment fragment = null;
    private int currentFragment = -1;

    private BottomNavigationView navigationView;
    private SharedPreferences sPref;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MainComponent mainComponent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInjector();

        toolbar = findViewById(R.id.toolbarTop);
        setSupportActionBar(toolbar);


        fragment = homeFragment;
        loadFragment(fragment);

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);





        sPref = getSharedPreferences("LoginPref", MODE_PRIVATE);
        String login = sPref.getString("Login", "");
        String password = sPref.getString("Password", "");

        compositeDisposable.add(NetworkService.getInstance(login, password)
                .getBomApi()
                .getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            Toast.makeText(this, res.getUserName(), Toast.LENGTH_SHORT).show();
                        },
                        err -> {
                            Toast.makeText(this, "error net seti", Toast.LENGTH_SHORT).show();
                        }
                )
        );

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            item.setChecked(true);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (currentFragment == AppConstants.HOME_FRAGMENT)
                        return false;
                    fragment = homeFragment;
                    break;
                case R.id.navigation_profile:
                    if (profileFragment == null)
                        profileFragment = new ProfileFragment();
                    if (currentFragment == AppConstants.PROFILE_FRAGMENT)
                        return false;
                    fragment = profileFragment;
                    break;
                case R.id.navigation_statistic:
                    //fragment = new HomeFragment();
                    break;
                case R.id.navigation_messages:
                   // fragment = new ProfileFragment();
                    break;
                case R.id.navigation_friends:
                    //fragment = new HomeFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };


    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }



    @Override
    public void onBackPressed() {
        if (currentFragment == AppConstants.HOME_FRAGMENT)
            tapPromtToExit(this);
        else
            super.onBackPressed();


    }

    @Override
    public void onFragmentChanged(int fragmentType) {
        currentFragment = fragmentType;
        navigationView.getMenu().getItem(fragmentType).setChecked(true);
    }

    private void initializeInjector() {
        this.mainComponent = DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public MainComponent getComponent() {
        return mainComponent;
    }
}
