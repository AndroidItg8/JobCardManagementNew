package com.itg.jobcardmanagement.registration.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itg.jobcardmanagement.registration.fragment.CustomerBasicInfoFragment;
import com.itg.jobcardmanagement.registration.fragment.CustomerVehicleInfoFragment;
import com.itg.jobcardmanagement.registration.model.RegistrationModel;
import com.itg.jobcardmanagement.registration.model.UserVehicleDetailModel;

/**
 * Created by me  Android itg 8 on 8/5/2017.
 */

public class  RegistrationViewPagerAdapter extends FragmentPagerAdapter {


    private UserVehicleDetailModel model;

    public RegistrationViewPagerAdapter(FragmentManager fm, UserVehicleDetailModel model) {
        super(fm);
        this.model = model;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
              return CustomerBasicInfoFragment.newInstance(model);
            case 1:
                 return  CustomerVehicleInfoFragment.newInstance(model);
//            case 2:
//                return  CustomerVehicleInfoFragment.newInstance(model);

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
