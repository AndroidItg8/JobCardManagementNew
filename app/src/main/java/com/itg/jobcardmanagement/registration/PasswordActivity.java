package com.itg.jobcardmanagement.registration;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.common.CommonMethod;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.itg.jobcardmanagement.registration.activity.LoginActivity.REG;

public class PasswordActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_username)
    TextView txtUsername;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fragment_container_login)
    FrameLayout fragmentContainerLogin;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (getIntent().hasExtra(CommonMethod.USERNAME)) {
            txtUsername.setText(getIntent().getStringExtra(CommonMethod.USERNAME));
        }

        int type=getIntent().getIntExtra(CommonMethod.TYPE,1);
        if(type==REG){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_login,RegistrationFragment.newInstance("",""),RegistrationFragment.class.getSimpleName()).commit();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_login,PasswordFragment.newInstance("",""),PasswordFragment.class.getSimpleName()).commit();
        }

    }



}
