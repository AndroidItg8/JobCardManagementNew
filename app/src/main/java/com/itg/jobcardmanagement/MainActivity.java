package com.itg.jobcardmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.itg.jobcardmanagement.common.Prefs;
import com.itg.jobcardmanagement.intro.IntroActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isFirstStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent i = new Intent(MainActivity.this, IntroActivity.class);
//        startActivity(i);

    }
}
