package com.itg.jobcardmanagement.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.common.CommonMethod;
import com.itg.jobcardmanagement.home.adapter.HomeViewPagerAdapter;
import com.itg.jobcardmanagement.home.fragment.DocumentFragmnet;
import com.itg.jobcardmanagement.home.fragment.FeedBackFragment;
import com.itg.jobcardmanagement.home.fragment.ServiceFragment;
import com.itg.jobcardmanagement.registration.CustomerRegistrationActivity;
import com.itg.jobcardmanagement.setting.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt_model)
    TextView txtModel;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private boolean isFirstStart;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpViewPager(viewpager);
        tabLayout.setupWithViewPager(viewpager);
//        Intent i = new Intent(MainActivity.this, IntroActivity.class);
//        startActivity(i);

    }

    private void setUpViewPager(ViewPager viewpager) {

            HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new ServiceFragment(), "Service");
            adapter.addFragment(new DocumentFragmnet(), "Document");
            adapter.addFragment(new FeedBackFragment(), "Feedback");
        viewpager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        intent = new Intent(getApplicationContext(), SettingActivity.class);

        switch (item.getItemId()) {
            case R.id.home:
                onBackPressed();
                break;
            case R.id.action_add:
                startActivity(new Intent(getApplicationContext(), CustomerRegistrationActivity.class));

                break;
            case R.id.action_profile:
                intent.putExtra(CommonMethod.FROMPROFILE, " ");
                callSettingActivity(intent);
                break;
            case R.id.action_qr:
                intent.putExtra(CommonMethod.FROMQR, " ");
                callSettingActivity(intent);
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
