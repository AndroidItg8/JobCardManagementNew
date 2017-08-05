package com.itg.jobcardmanagement.common;

import android.app.Application;

/**
 * Created by itg_Android on 8/4/2017.
 */

public class MyApplication extends Application {
    private static final String MY_PREF_NAME = "JobCardPrefClass";
    private static MyApplication mInstance;

    public static MyApplication getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        mInstance.initialisePeference();
    }

    private void initialisePeference() {
        new Prefs.Builder()
                .setContext(this)
                .setMode(MODE_PRIVATE)
                .setPrefsName(MY_PREF_NAME)
                .setUseDefaultSharedPreference(true)
                .build();
    }

}
