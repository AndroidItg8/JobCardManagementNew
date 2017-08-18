package com.itg.jobcardmanagement.registration.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.common.CommonMethod;
import com.itg.jobcardmanagement.common.Logger;
import com.itg.jobcardmanagement.common.Prefs;
import com.itg.jobcardmanagement.home.HomeActivity;
import com.itg.jobcardmanagement.home.MainActivity;
import com.itg.jobcardmanagement.registration.CustomerRegistrationActivity;
import com.itg.jobcardmanagement.registration.PasswordActivity;
import com.itg.jobcardmanagement.registration.mvp.LoginPresenterImp;
import com.itg.jobcardmanagement.registration.mvp.LoginRegMVP;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginRegMVP.LoginView, GoogleApiClient.OnConnectionFailedListener {

    public static final int REG = 1;
    public static final int PASS = 2;
    private static final int RC_SIGN_IN = 102;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int INTENT_PASSWORD = 103;

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
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    private String username;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;

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

        if(Prefs.contains(CommonMethod.USERNAME)){
            if(Prefs.contains(CommonMethod.USER_PROFILE_UPDATED)) {
                startActivity(new Intent(this, MainActivity.class));
            }else {
                startActivity(new Intent(this, CustomerRegistrationActivity.class));
            }
            finish();
        }

        mAuth = FirebaseAuth.getInstance();
        presenter = new LoginPresenterImp(this);
    }

    @OnClick({R.id.btn_next, R.id.txt_signup, R.id.btn_gplus})
    public void onClick(View v) {
        if (v.getId() == R.id.btn_next) {
            inEmail.setError(null);
            presenter.onUsernameSubmit(edtEmail.getText().toString());
        } else if (v.getId() == R.id.btn_gplus) {
            startGPlusLogin();
        }
    }

    private void startGPlusLogin() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
                Logger.e("Fail to startGoogleClient");
            }
        } else if (requestCode == INTENT_PASSWORD) {
            finishActivity(INTENT_PASSWORD);
            finish();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();


            presenter = new LoginPresenterImp(this);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mGoogleApiClient!=null) {
            mGoogleApiClient.stopAutoManage(this);
            mGoogleApiClient.disconnect();
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
    public void onUsernameFieldEmpty() {
        inEmail.setError(getString(R.string.username_is_empty));
    }

    @Override
    public void onVerificationFailed(String message) {
        startRegActivity(getUsername(), message, REG);
    }

    private void startRegActivity(String username, String profilePic, int type) {
        Prefs.putString(CommonMethod.USER_EMAIL_OR_PHONE_NUMBER, getUsername());
        Intent intent = new Intent(this, PasswordActivity.class);
        intent.putExtra(CommonMethod.USERNAME, username);
        intent.putExtra(CommonMethod.PROFILE_PIC, profilePic);
        intent.putExtra(CommonMethod.TYPE, type);
        startActivityForResult(intent, INTENT_PASSWORD);
    }


    @Override
    public void onPasswordNotMatch() {

    }

    @Override
    public void onUserFound(String userId, String profilePicUrl) {

        Prefs.putString(CommonMethod.USERNAME, userId);
        startRegActivity(getUsername(), profilePicUrl, PASS);
    }

    @Override
    public void showProgress() {
        btnNext.setVisibility(View.GONE);
        btnFb.setEnabled(false);
        btnGplus.setEnabled(false);
        progressbar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        btnNext.setVisibility(View.VISIBLE);
        btnFb.setEnabled(true);
        btnGplus.setEnabled(true);
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public String getUsername() {
        return edtEmail.getText().toString();

    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Logger.e(connectionResult.getErrorMessage());
    }
}
