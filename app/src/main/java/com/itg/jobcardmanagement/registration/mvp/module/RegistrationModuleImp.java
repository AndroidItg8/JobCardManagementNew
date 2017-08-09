package com.itg.jobcardmanagement.registration.mvp.module;

import com.itg.jobcardmanagement.registration.model.RegistrationModel;
import com.itg.jobcardmanagement.registration.mvp.LoginRegMVP;

/**
 * Created by Android itg 8 on 8/7/2017.
 */

public class RegistrationModuleImp implements LoginRegMVP.RegistrationModule{


    private final LoginRegMVP.RegistrationListener listener;

    public RegistrationModuleImp(LoginRegMVP.RegistrationListener listener) {
        this.listener = listener;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onRegistrationDetailsSentToServer(RegistrationModel model) {
//         listener.onSaveSuccessfully("Sucess");
        listener.onUserNameNotMatch("FAiled");
      //  listener.onUserNameMatch("Failed");
    }


}
