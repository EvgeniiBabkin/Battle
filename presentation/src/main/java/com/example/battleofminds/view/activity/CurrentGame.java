package com.example.battleofminds.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.battleofminds.R;

public class CurrentGame extends BaseActivity {

    private Toolbar toolbar;

    // private MenuItem actionMenu, microMenu, removeMenu, sortMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_game);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


}
