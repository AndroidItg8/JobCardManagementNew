package com.itg.jobcardmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.itg.jobcardmanagement.common.Prefs;
import com.itg.jobcardmanagement.intro.IntroActivity;
import com.itg.jobcardmanagement.setting.SettingActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isFirstStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent i = new Intent(MainActivity.this, IntroActivity.class);
//        startActivity(i);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId())
       {
           case R.id.home:
               onBackPressed();
               break;
           case R.id.action_add:
               Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
               
               callSettingActivity(intent);
               break;
           case R.id.action_profile:
               //callSettingActivity();
               break;
           case R.id.action_qr:
              // callSettingActivity();
               break;
       }
        return super.onOptionsItemSelected(item);
    }

    private void callSettingActivity(Intent intent) {
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


}
