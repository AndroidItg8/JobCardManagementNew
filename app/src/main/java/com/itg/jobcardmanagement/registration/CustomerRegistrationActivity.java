package com.itg.jobcardmanagement.registration;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.doument.fragment.DocumentDailogueFragment;
import com.itg.jobcardmanagement.doument.fragment.NoTransferDocumnetFragment;
import com.itg.jobcardmanagement.registration.adapter.RegistrationViewPagerAdapter;
import com.itg.jobcardmanagement.registration.model.RegistrationModel;
import com.itg.jobcardmanagement.registration.mvp.LoginRegMVP;
import com.itg.jobcardmanagement.registration.mvp.presenter.RegistrationPresenterImp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class CustomerRegistrationActivity extends AppCompatActivity implements View.OnClickListener ,LoginRegMVP.RegistrationView, EasyPermissions.PermissionCallbacks {


    private static final String TAG = CustomerRegistrationActivity.class.getSimpleName();
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
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.rl_viewPager)
    RelativeLayout rlViewPager;
    @BindView(R.id.rl_no_item)
    RelativeLayout rlNoItem;
    @BindView(R.id.img_pre)
    ImageView imgPre;
    @BindView(R.id.img_next)
    ImageView imgNext;
    @BindView(R.id.btn_finished)
    Button btnFinished;
    private String registation;
    private String thesis;
    private RegistrationModel model;
    private LoginRegMVP.RegistrationPresenter presenter;
    private FragmentManager fm;
    private  static final int RC_CAMERA = 121;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nestedScrollingView.setFillViewport(true);
        checkApiVersion();
        checkRegistrationModel();


    }

    private void checkRegistrationModel() {
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
//        readPermission();
        model = new RegistrationModel();
        edtRegistration.setOnClickListener(this);
        edtThesis.setOnClickListener(this);
        fab.setOnClickListener(this);
        lblRegistration.setOnClickListener(this);
        lblThesis.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        imgPre.setOnClickListener(this);
        btnFinished.setOnClickListener(this);
        presenter = new RegistrationPresenterImp(this);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "Position:" + position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    private void openBottomSheet(final boolean isFromRegistration) {
        final Dialog mBottomSheetDialog = new Dialog(this,
                R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(R.layout.layout_textview);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        OpenSoftKeyboard();
        final EditText editText = (EditText) mBottomSheetDialog.findViewById(R.id.edt_common);
        Button btnDone = (Button) mBottomSheetDialog.findViewById(R.id.btn_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFromRegistration) {
                    registation = edtRegistration.getText().toString();
                    edtRegistration.setText(editText.getText().toString());
                    model.setRegistrationNumber(editText.getText().toString());


                } else {
                    thesis = edtThesis.getText().toString();
                    edtThesis.setText(editText.getText().toString());
                    model.setChesisNumber(editText.getText().toString());


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
                presenter.onsendRegistrationInfoToServer(model);
                break;
            case R.id.lbl_registration:
                // openBottomSheet(true);
                break;
            case R.id.lbl_thesis:
                //openBottomSheet(false);
                break;
            case R.id.img_next:
                checkFragmentPosition();

                break;
            case R.id.img_pre:
                if (viewpager.getCurrentItem() != 0) {
                    viewpager.setCurrentItem(viewpager.getCurrentItem() - 1);
                    if (btnFinished.getVisibility() == View.VISIBLE) {
                        btnFinished.setVisibility(View.GONE);
                        imgPre.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.btn_finished:
                //   checkFragmentUsingViewPagerPosition();
                break;
        }
    }

    private void checkPossibilites() {

        if(model.getRegistrationNumber().equalsIgnoreCase(edtRegistration.getText().toString())
                && model.getChesisNumber().equalsIgnoreCase(edtThesis.getText().toString()))
        {
            viewpager.setAdapter(new RegistrationViewPagerAdapter(getSupportFragmentManager(), model));
        }else
        {

        }
    }

    @AfterPermissionGranted(RC_CAMERA)
    private void readPermission() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            openDocumnetDailogue();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.camera_rationale),
                    RC_CAMERA, perms);
        }
    }
    private void checkFragmentPosition() {
        switch (viewpager.getCurrentItem())
        {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;

        }
    }
//    private void checkFragmentUsingViewPagerPosition() {
//        switch (signUpViewPager.getCurrentItem()) {
//            case 0:
//                if (userFragmentListener != null) {
//                    //  userFragmentListener.onUserProfileListerner(userProfileModel);
//                    userFragmentListener.onuserListener();
//                }
//                break;
//            case 1:
//                if (ngoFragmentListener != null) {
//                    // ngoFragmentListener.onNGOProfileUpateListerner(userProfileModel);
//                    ngoFragmentListener.onNgoProfileListener();
//                }
//                break;
//            case 2:
//                if (ngoAccountFragmentListener != null) {
//                    //  ngoAccountFragmentListener.onProfileUpateAccountListerner(userProfileModel);
//                    ngoAccountFragmentListener.onNgoAccountListener();
//                }
//                break;
//
//
//      }
//    }


    private void OpenSoftKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(
                coordinator.getApplicationWindowToken(),
                InputMethodManager.SHOW_FORCED, 0);
    }

    private void sendDataToServer() {
        if (TextUtils.isEmpty(edtRegistration.getText().toString())) {
            rlRegistration.setVisibility(View.VISIBLE);
        } else {
            rlFilledRegistration.setVisibility(View.VISIBLE);
            rlRegistration.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(registation)) {
            txtRegistration.setText(registation);
        }
        if (!TextUtils.isEmpty(thesis)) {
            txtRegistration.setText(thesis);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        if (item.getItemId() == R.id.action_add) {
            clearRegistrationNumber();
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearRegistrationNumber() {
        edtThesis.setText(" ");
        edtRegistration.setText(" ");
    }


    public void sendVehicleDataToActivity(String series, String modelCode, String colorCode, String sellingDate, String vinNumber, String delearName) {
        model.setCarSeries(series);
        model.setCarModelCode(modelCode);
        model.setCarColorCode(colorCode);
        model.setCarSaleDate(sellingDate);
        model.setCarVinNumber(vinNumber);
        model.setCarDelearName(delearName);
        btnFinished.setVisibility(View.VISIBLE);
        imgNext.setVisibility(View.GONE);
        imgPre.setVisibility(View.GONE);

    }

    public void sendBaiscInfoToActivity(String name, String lName, String contact, String address, String city, String state) {
        model.setFirstName(name);
        model.setLastName(lName);
        model.setContactNumber(contact);
        model.setCustomerAddress(address);
        model.setCustomerCity(city);
        model.setCustomerState(state);
        btnFinished.setVisibility(View.GONE);
        imgNext.setVisibility(View.VISIBLE);
        imgPre.setVisibility(View.VISIBLE);

    }

    @Override
    public void onNoNetworkAvailable() {

    }

    @Override
    public void onNetworkAvailable() {

    }

    @Override
    public void onShowProgress() {

    }

    @Override
    public void onHideProgress() {


    }

    @Override
    public void onSendRegistrationDetailsToServer(RegistrationModel model) {

    }

    @Override
    public void onFeildError(EditText... editTexts) {

    }

    @Override
    public void onSaveRegistartionSuccesfully(String msg) {
        checkPossibilites();
    }

    @Override
    public void onUserNotMatch(String msg) {
        openDocumnetDailogue();

    }

    @Override
    public void onUserNameMatch(String failed) {
        openQueryForm();

    }
    private void openQueryForm() {
        fm = getSupportFragmentManager();
        NoTransferDocumnetFragment fragment=   new NoTransferDocumnetFragment();
        fragment.show(fm,"");
    }


    public void sendQueryFormToServer(String title, String description) {


    }

    private void openDocumnetDailogue() {
        fm = getSupportFragmentManager();
        DocumentDailogueFragment fragment=   new DocumentDailogueFragment();
        fragment.show(fm,"");

    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {


    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public void onDocumentSaveToSever(ArrayList<String> documnetFileList) {

    }
}
