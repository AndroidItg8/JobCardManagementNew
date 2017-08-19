package com.itg.jobcardmanagement.common;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by me  Android itg 8 on 8/7/2017.
 */

public class  CommonMethod {
    public static final String FROMADD = "FROMADD";
    public static final String FROMPROFILE = "FROMPROFILE";
    public static final String FROMQR = "FROMQR";

    public static final String USERNAME = "username";
    public static final String PROFILE_PIC = "Profile_pic";

    public static final String TYPE = "TYPE";
    public static final String REG_REQ = "reg_req";
    public static final String USER_EMAIL_OR_PHONE_NUMBER = "USER_EMAIL_OR_PHONE_NUMBER";

    public static final String APPNAME = "JobCardManagement";

    public static final String TOKEN = "tkn";
    public static final String USER_PROFILE_UPDATED = "user_profile_update";
    public static final String FLAG = "flag";
    public static final String USERNAME_TYPE = "username_type";
    public static final String USERNAME_INSERTED = "username_insertded";
    public static final String SELECTED_CAR = "selected_car";


    public interface NextButtonClickProfileListner {
        void onNextButtonClicked();
    }

    public interface NextButtonClickedVehicleListener{
        void onNextButtonClicked();
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
}
