package com.itg.jobcardmanagement.registration;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.registration.adapter.RegistrationViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerRegistrationActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.lbl_skip)
    TextView lblSkip;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lbl_car)
    TextView lblCar;
    @BindView(R.id.edt_registration)
    EditText edtRegistration;
    @BindView(R.id.lbl_registration)
    TextView lblRegistration;
    @BindView(R.id.edt_thesis)
    EditText edtThesis;
    @BindView(R.id.txt_registration)
    TextView txtRegistration;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.custom_fab)
    ImageButton customFab;
    @BindView(R.id.rl_registration)
    RelativeLayout rlRegistration;
    @BindView(R.id.txt_thesis)
    TextView txtThesis;
    @BindView(R.id.rl_filled_registration)
    RelativeLayout rlFilledRegistration;
    @BindView(R.id.lbl_thesis)
    TextView lblThesis;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.nestedScrollingView)
    NestedScrollView nestedScrollingView;
    private String registation;
    private String thesis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nestedScrollingView.setFillViewport(true);
        viewpager.setAdapter(new RegistrationViewPagerAdapter(getSupportFragmentManager()));
        checkApiVersion();


    }

    private void checkApiVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setVisibility(View.VISIBLE);
        } else {
            customFab.setVisibility(View.VISIBLE);
        }
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        edtRegistration.setOnClickListener(this);
        edtThesis.setOnClickListener(this);
        fab.setOnClickListener(this);
        lblRegistration.setOnClickListener(this);
        lblThesis.setOnClickListener(this);
        sendDataToServer();


    }

    private void openBottomSheet(final boolean isFromRegistration) {
        final Dialog mBottomSheetDialog = new Dialog(this,
                R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(R.layout.layout_textview);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        final EditText editText = (EditText) mBottomSheetDialog.findViewById(R.id.edt_common);
        Button btnDone = (Button) mBottomSheetDialog.findViewById(R.id.btn_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFromRegistration) {
                    registation = edtRegistration.getText().toString();
                    edtRegistration.setText(editText.getText().toString());


                } else {
                    thesis = edtThesis.getText().toString();

                    edtThesis.setText(editText.getText().toString());

                }
                mBottomSheetDialog.dismiss();

            }
        });


        mBottomSheetDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edt_registration:
                openBottomSheet(true);
                break;
            case R.id.edt_thesis:
                openBottomSheet(false);
                break;
            case R.id.fab:
                break;
            case R.id.lbl_registration:
                openBottomSheet(true);
                break;
            case R.id.lbl_thesis:
                openBottomSheet(false);
                break;
        }
    }

    private void sendDataToServer() {
        if (TextUtils.isEmpty(edtRegistration.getText().toString())) {
            rlRegistration.setVisibility(View.VISIBLE);
        } else {
            rlFilledRegistration.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(registation)) {
                txtRegistration.setText(registation);

            }
            if (!TextUtils.isEmpty(thesis)) {
                txtRegistration.setText(thesis);

            }
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
