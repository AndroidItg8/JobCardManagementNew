package com.itg.jobcardmanagement.registration.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itg.jobcardmanagement.registration.fragment.CustomerBasicInfoFragment;

/**
 * Created by Android itg 8 on 8/5/2017.
 */

public class RegistrationViewPagerAdapter extends FragmentPagerAdapter {


    public RegistrationViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
              return  new CustomerBasicInfoFragment();
            case 1:
                 return  new CustomerVehicleInfoFragment();
            case 2:
                return  new CustomerVehicleInfoFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
