package com.itg.jobcardmanagement.home;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.common.CommonMethod;
import com.itg.jobcardmanagement.home.adapter.ExteriorListAdapter;
import com.itg.jobcardmanagement.home.adapter.HomeViewPagerAdapter;
import com.itg.jobcardmanagement.home.fragment.DocumentFragmnet;
import com.itg.jobcardmanagement.home.fragment.FeedBackFragment;
import com.itg.jobcardmanagement.home.fragment.ServiceFragment;
import com.itg.jobcardmanagement.home.fragment.VehicleListDialogueFragment;
import com.itg.jobcardmanagement.registration.CustomerRegistrationActivity;
import com.itg.jobcardmanagement.setting.SettingActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.txt_model)
    TextView txtModel;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.img_camera)
    ImageView imgCamera;
    @BindView(R.id.img_car)
    ImageView imgCar;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.img_list)
    ImageView imgList;
    @BindView(R.id.ll_car)
    LinearLayout llCar;
    @BindView(R.id.lbl_exterior)
    TextView lblExterior;
    @BindView(R.id.img_exterior)
    ImageView imgExterior;
    @BindView(R.id.txt_exterior)
    TextView txtExterior;
    @BindView(R.id.ll_exterior)
    LinearLayout llExterior;
    @BindView(R.id.lbl_interior)
    TextView lblInterior;
    @BindView(R.id.img_interior)
    ImageView imgInterior;
    @BindView(R.id.txt_interior_value)
    TextView txtInteriorValue;
    @BindView(R.id.ll_interior)
    LinearLayout llInterior;
    @BindView(R.id.lbl_mileage)
    TextView lblMileage;
    @BindView(R.id.img_mileage)
    ImageView imgMileage;
    @BindView(R.id.txt_mileage_value)
    TextView txtMileageValue;
    @BindView(R.id.ll_mileage)
    LinearLayout llMileage;
    @BindView(R.id.frameLayout_setting)
    FrameLayout frameLayoutSetting;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private boolean isFirstStart;
    private Intent intent;
    private FragmentManager fm;
    private long idOfColor;
    private HashMap<String, Integer> list_Exterior= new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        txtModel.setOnClickListener(this);
        setUpViewPager(viewpager);
        tabLayout.setupWithViewPager(viewpager);
        llExterior.setOnClickListener(this);
        llInterior.setOnClickListener(this);
        llMileage.setOnClickListener(this);

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

    private void OpenBottomSheetForExterior() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.item_car_extrior);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        bottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        TextView txtSet = (TextView) bottomSheetDialog.findViewById(R.id.txt_set);
        TextView txtCancel = (TextView) bottomSheetDialog.findViewById(R.id.txt_cancel);
        ListView listExterior = (ListView) bottomSheetDialog.findViewById(R.id.list_exterior_color);
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();

            }
        });
        txtSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "OnCliked:" + idOfColor);


            }
        });
        String[] carLabel = getResources().getStringArray(R.array.car_exterior);
        int[] carColor = getResources().getIntArray(R.array.car_color);
        for (String txt : carLabel) {

           // for (Integer color : carColor) {
                list_Exterior.put(txt, 1);
                Log.d(TAG,"List:"+list_Exterior);
           // }
        }
        if (listExterior != null) {
            listExterior.setAdapter(new ExteriorListAdapter(getApplicationContext(), list_Exterior));
            listExterior.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    idOfColor = id;
                }
            });
            bottomSheetDialog.show();
        }
    }

    private void OpenCarModelFragment() {

        fm = getSupportFragmentManager();
        VehicleListDialogueFragment fragment = new VehicleListDialogueFragment();
        fragment.show(fm, "");

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_model:
                OpenCarModelFragment();
                break;
            case R.id.ll_exterior:
                OpenBottomSheetForExterior();
                break;
            case R.id.ll_interior:

                break;
            case R.id.ll_mileage:
                break;


        }
    }
}
