package com.itg.jobcardmanagement.registration;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.common.CommonMethod;
import com.itg.jobcardmanagement.common.Logger;
import com.itg.jobcardmanagement.common.Prefs;
import com.itg.jobcardmanagement.doument.fragment.DocumentDailogueFragment;
import com.itg.jobcardmanagement.doument.fragment.NoTransferDocumnetFragment;
import com.itg.jobcardmanagement.home.MainActivity;
import com.itg.jobcardmanagement.registration.adapter.RegistrationViewPagerAdapter;
import com.itg.jobcardmanagement.registration.model.RegistrationModel;
import com.itg.jobcardmanagement.registration.model.User;
import com.itg.jobcardmanagement.registration.model.UserVehicleDetailModel;
import com.itg.jobcardmanagement.registration.model.Vehicle;
import com.itg.jobcardmanagement.registration.mvp.LoginRegMVP;
import com.itg.jobcardmanagement.registration.mvp.presenter.RegistrationPresenterImp;
import com.itg.jobcardmanagement.widget.PinEntryEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class CustomerRegistrationActivity extends AppCompatActivity implements View.OnClickListener, LoginRegMVP.RegistrationView, EasyPermissions.PermissionCallbacks {


    private static final String TAG = CustomerRegistrationActivity.class.getSimpleName();
    private static final int RC_CAMERA = 121;
    @BindView(R.id.lbl_skip)
    TextView lblSkip;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lbl_car)
    TextView lblCar;
    @BindView(R.id.edt_registration)
    PinEntryEditText edtRegistration;
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
    @BindView(R.id.fab_previous)
    FloatingActionButton fabPrevious;
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
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.frmFabLayout)
    FrameLayout frmFabLayout;

    private String registation;
    private String thesis;
    private RegistrationModel model;
    private LoginRegMVP.RegistrationPresenter presenter;
    private FragmentManager fm;
    private RegistrationViewPagerAdapter adapter;
    private View fabView;
    private CommonMethod.NextButtonClickProfileListner listener;
    private CommonMethod.NextButtonClickedVehicleListener vehicleListener;
    private ProgressDialog progressview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new RegistrationPresenterImp(this);
        nestedScrollingView.setFillViewport(true);
        checkApiVersion();
        checkRegistrationModel();


    }

    private void checkRegistrationModel() {
        presenter.checkUsersDetails();
    }

    private void checkApiVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setVisibility(View.VISIBLE);
            fabView = fab;
        } else {
            customFab.setVisibility(View.VISIBLE);
            fabView = customFab;
        }
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
//        readPermission();
        model = new RegistrationModel();
        edtRegistration.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                Logger.i(String.valueOf(str));
            }
        });
        edtRegistration.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        edtRegistration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String text;
                if (count == 2 || count == 5) {
                    text = charSequence + "-";
                    Logger.i(text);
                    edtRegistration.setText(text);
                    edtRegistration.setSelection(++count);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//        edtRegistration.setOnClickListener(this);
        fabView.setOnClickListener(this);
        lblRegistration.setOnClickListener(this);
        txtRegistration.setOnClickListener(this);
        txtThesis.setOnClickListener(this);
        lblThesis.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        imgPre.setOnClickListener(this);
        btnFinished.setOnClickListener(this);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "Position:" + position);
//                listner = (CommonMethod.NextButtonClickProfileListner) adapter.getItem(position);
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
            case R.id.custom_fab:
                presenter.onsendRegistrationInfoToServer(model);
            case R.id.lbl_registration:
                // openBottomSheet(true);
                break;
            case R.id.lbl_thesis:
                //openBottomSheet(false);
                break;
            case R.id.img_next:

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
            case R.id.txt_registration:
                onShowcaseHide();
                break;
            case R.id.txt_thesis:
                onShowcaseHide();
        }
    }

    private void checkPossibilites(UserVehicleDetailModel model) {
        adapter = new RegistrationViewPagerAdapter(getSupportFragmentManager(), model);
        viewpager.setAdapter(adapter);
//        listner = (CommonMethod.NextButtonClickProfileListner) adapter.getItem(0);
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


    public void sendVehicleDataToActivity(Vehicle vehicle) {
        vehicle.setVehicleNo(getRegNumber());
        vehicle.setChessesNo(getChesisNumber());
        presenter.onVehicleDetailsAvailable(vehicle);

    }

    @Override
    public void onVehicleStorageSuccess(String carid) {
        Prefs.putString(CommonMethod.SELECTED_CAR, carid);
    }

    @Override
    public void showProfileDownloadProgress() {
        if (progressview == null)
            progressview = new ProgressDialog(this);

        progressview.setTitle("Loading");
        progressview.setMessage("Please wait while we checking your previous car entries...");
        progressview.setCancelable(true);
        progressview.show();
    }

    @Override
    public void hideProfileDownloadProgress() {
        if (progressview != null && progressview.isShowing())
            progressview.dismiss();
    }

    @Override
    public void onVehicleStorageFail() {
        Toast.makeText(this, "Fail to save.", Toast.LENGTH_SHORT).show();
    }

    public void sendBaiscInfoToActivity(User user) {
        presenter.onUserProfileAvail(user);

    }

    @Override
    public void onProfileSuccessfullySaved() {
        btnFinished.setVisibility(View.GONE);
        imgNext.setVisibility(View.VISIBLE);
        fabPrevious.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Profile saved...", Toast.LENGTH_SHORT).show();
        if (viewpager.getCurrentItem() + 1 < 2)
            viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
    }

    @Override
    public void onNoNetworkAvailable() {
        Toast.makeText(this, "Error in network. Please try again", Toast.LENGTH_SHORT).show();
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

    }

    @Override
    public void onUserNotMatch(Object msg) {
        openDocumnetDailogue();

    }

    @Override
    public void onUserNameMatch(Object obj) {
        UserVehicleDetailModel model = new Gson().fromJson(obj.toString(), new TypeToken<UserVehicleDetailModel>() {
        }.getType());
        Logger.i(new Gson().toJson(model));
        checkPossibilites(model);
    }

    @Override
    public String getRegNumber() {
        String regNo = edtRegistration.getText().toString();
        if (regNo != null) {
            return regNo.replaceAll("[^A-Z0-9]+", "");
        }
        return null;
    }

    @Override
    public String getChesisNumber() {
        return edtThesis.getText().toString();
    }

    @Override
    public void onInvalidRegNumber(String err) {
        edtRegistration.setError(err);
    }

    @Override
    public void onInvalidChasisNo(String err) {
        edtThesis.setError(err);
    }

    @Override
    public void onUserNotAvailable(String s) {
        showNextButtonOnly();
        checkPossibilites(null);
    }

    private void showNextButtonOnly() {
        imgNext.setVisibility(View.VISIBLE);
        imgPre.setVisibility(View.GONE);
        btnFinished.setVisibility(View.GONE);
    }

    private void openQueryForm() {
        fm = getSupportFragmentManager();
        NoTransferDocumnetFragment fragment = new NoTransferDocumnetFragment();
        fragment.show(fm, "");
    }


    public void sendQueryFormToServer(String title, String description) {


    }

    private void openDocumnetDailogue() {
        fm = getSupportFragmentManager();
        DocumentDailogueFragment fragment = new DocumentDailogueFragment();
        fragment.show(fm, "");

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

    @Override
    public void startFabProgress() {
        fabView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFabProgress() {
        fabView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setShowcaseRegAndChesis(String regNumber, String chasisNumber) {
        txtRegistration.setText("REG NO. : " + regNumber);
        txtThesis.setText("CHASSIS NO. : " + chasisNumber);
    }

    @Override
    public void onShowcaseShow() {
        rlFilledRegistration.setVisibility(View.VISIBLE);
        rlRegistration.setVisibility(View.GONE);
        fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_next));
        SlideToDown();
    }

    private void onShowcaseHide() {
        rlFilledRegistration.setVisibility(View.GONE);
        rlRegistration.setVisibility(View.VISIBLE);
        fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_done_black_24dp));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SlideToAbove();
            }
        }, 400);
    }

    @Override
    public void onHideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (getCurrentFocus() != null)
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void startGettingUserProfileData() {
        listener.onNextButtonClicked();
    }

    @Override
    public void startGettingVehicleData() {
        vehicleListener.onNextButtonClicked();
    }

    @Override
    public void finishActivityRegComplete() {
        Prefs.putString(CommonMethod.USER_PROFILE_UPDATED, "fdon");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void SlideToDown() {
        Animation slide = null;
        slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 5.2f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        frmFabLayout.startAnimation(slide);

        slide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                frmFabLayout.clearAnimation();

                CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(
                        frmFabLayout.getWidth(), frmFabLayout.getHeight());
//                int dp16= (int) CommonMethod.convertDpToPixel(16f,getApplicationContext());
//                lp.setMargins(dp16, frmFabLayout.getWidth(), dp16, dp16);
                int dp25 = (int) CommonMethod.convertDpToPixel(25f, getApplicationContext());
                lp.setMargins(dp25, dp25, dp25, dp25);
                lp.gravity = Gravity.BOTTOM | Gravity.END;
                frmFabLayout.setLayoutParams(lp);

            }

        });

    }


    public void SlideToAbove() {
        Animation slide = null;
        slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, -3.2f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        frmFabLayout.startAnimation(slide);

        slide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                frmFabLayout.clearAnimation();

                CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(
                        frmFabLayout.getWidth(), frmFabLayout.getHeight());
                int dp25 = (int) CommonMethod.convertDpToPixel(25f, getApplicationContext());
                lp.setMargins(dp25, dp25, dp25, dp25);
                lp.setAnchorId(R.id.app_bar);
                lp.anchorGravity = Gravity.BOTTOM | Gravity.END;
                frmFabLayout.setLayoutParams(lp);

            }

        });

    }

    public void setListener(CommonMethod.NextButtonClickProfileListner listener) {
        this.listener = listener;
    }

    public void setListener(CommonMethod.NextButtonClickedVehicleListener listener) {
        this.vehicleListener = listener;
    }
}
