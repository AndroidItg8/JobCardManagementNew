package com.itg.jobcardmanagement.service;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.home.adapter.HomeViewPagerAdapter;
import com.itg.jobcardmanagement.home.fragment.DocumentFragmnet;
import com.itg.jobcardmanagement.home.fragment.FeedBackFragment;
import com.itg.jobcardmanagement.home.fragment.ServiceFragment;
import com.itg.jobcardmanagement.service.adapter.ServiceViewPagerAdapter;
import com.itg.jobcardmanagement.service.fragment.CheckVehicleConditionFragment;
import com.itg.jobcardmanagement.service.fragment.ServiceInfoFragment;
import com.itg.jobcardmanagement.service.fragment.TechnicalSpificationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpViewPager(viewpager);
        tabLayout.setupWithViewPager(viewpager);


    }

    private void setUpViewPager(ViewPager viewpager) {
        ServiceViewPagerAdapter adapter = new ServiceViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new ServiceInfoFragment(), "Service Info");
            adapter.addFragment(new CheckVehicleConditionFragment(), "Physical Facts");
            adapter.addFragment(new TechnicalSpificationFragment(), "Technical Facts");
            viewpager.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
