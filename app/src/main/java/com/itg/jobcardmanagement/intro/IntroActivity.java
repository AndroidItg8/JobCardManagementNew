package com.itg.jobcardmanagement.intro;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itg.jobcardmanagement.registration.CustomerRegistrationActivity;
import com.itg.jobcardmanagement.registration.LoginActivity;
import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.common.Logger;
import com.itg.jobcardmanagement.common.Prefs;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.layoutDots)
    LinearLayout layoutDots;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.btn_skip)
    Button btnSkip;
    @BindView(R.id.fragment_container_intro)
    FrameLayout fragmentContainerIntro;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private TextView[] dots;
    private int[] colorArray;
    private boolean isFirstStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                //  Create a new boolean and preference and set it to true
                isFirstStart = Prefs.getBoolean("firstStart", true);

                //  Check either activity or app is open very first time or not and do action
                if (!isFirstStart) {
                    //  Launch application introduction screen
                  finishIntro();

                }
            }
        });
        t.start();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        colorArray=getResources().getIntArray(R.array.array_intro_page);
// Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        addBottomDots(0);
        changeStatusBarColor(0);

        IntroAdapter adapter = new IntroAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
//        viewpager.setBackgroundResource(R.drawable.background_jpeg);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                addBottomDots(position);
                changeButtonState(position);
            }

            @Override
            public void onPageSelected(int position) {
                Logger.i(String.valueOf(position));
                changeStatusBarColor(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

        btnNext.setOnClickListener(this);
        btnSkip.setOnClickListener(this);

    }

    private void changeButtonState(int position) {
        if(position==2){
            btnNext.setText("FINISH");
            btnSkip.setVisibility(View.GONE);
        }else {
            btnSkip.setVisibility(View.VISIBLE);
            btnNext.setText("NEXT");
        }
    }

    /**
     * Making notification bar transparent
     * @param position
     */
    private void changeStatusBarColor(int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(colorArray[position]);
            toolbar.setBackgroundColor(colorArray[position]);
        }
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[3];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        layoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            if (Build.VERSION.SDK_INT >= 24) {
                dots[i].setText(Html.fromHtml("&#8226;", Html.FROM_HTML_MODE_LEGACY));
            } else {
                dots[i].setText(Html.fromHtml("&#8226;"));
            }
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            layoutDots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_next){
            if(viewpager.getCurrentItem()==2)
                finishIntro();
            else
                viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
        }else if(view.getId()==R.id.btn_skip)
            finishIntro();
    }

    private void finishIntro() {
        Prefs.putBoolean("firstStart", false);
        startActivity(new Intent(this, CustomerRegistrationActivity.class));
        finish();
    }


    public class IntroAdapter extends FragmentPagerAdapter {


        IntroAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return IntroFragment.newInstance(position,colorArray[position]);
        }


        @Override
        public int getCount() {
            return 3;
        }
    }
}
