package com.itg.jobcardmanagement.registration;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.registration.mvp.LoginPresenterImp;
import com.itg.jobcardmanagement.registration.mvp.LoginRegMVP;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginRegMVP.LoginView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.btn_fb)
    TextView btnFb;
    @BindView(R.id.btn_gplus)
    TextView btnGplus;
    @BindView(R.id.ll_social)
    LinearLayout llSocial;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.lbl_or)
    TextView lblOr;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.in_email)
    TextInputLayout inEmail;
    @BindView(R.id.txt_signup_lbl)
    TextView txtSignupLbl;
    @BindView(R.id.txt_signup)
    TextView txtSignup;

    LoginRegMVP.LoginPresenter presenter;
    @BindView(R.id.btn_next)
    Button btnNext;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        presenter = new LoginPresenterImp(this);

    }

    @OnClick({R.id.btn_next,R.id.txt_signup})
    public void onClick(View v){
        if(v.getId()==R.id.btn_next){
            inEmail.setError(null);
            presenter.onUsernameSubmit(edtEmail.getText().toString());
        }
    }

    @Override
    public void onEmailEntered(String email) {
        setUsername(email);
    }

    @Override
    public void onMobileNumberEntered(String mobileNo) {
        setUsername(mobileNo);
    }

    @Override
    public void onEmailInvalid() {
        inEmail.setError(getString(R.string.email_invalid));
    }

    @Override
    public void onMobileInvalid() {
        inEmail.setError(getString(R.string.mobile_invalid));
    }

    @Override
    public void onVerificationFailed(String message) {

    }

    @Override
    public void onPasswordNotMatch() {

    }

    @Override
    public void onUserFound(String userId, String profilePicUrl) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    public void setUsername(String username) {
        this.username = username;
    }
}
