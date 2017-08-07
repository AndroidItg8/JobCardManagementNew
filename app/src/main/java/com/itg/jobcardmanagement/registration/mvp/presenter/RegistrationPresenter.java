package com.itg.jobcardmanagement.registration.mvp.presenter;

import com.itg.jobcardmanagement.common.VolleyControler;
import com.itg.jobcardmanagement.common.common_interface.BasePresenter;
import com.itg.jobcardmanagement.registration.model.RegistrationModel;

/**
 * Created by Android itg 8 on 8/7/2017.
 */

public interface RegistrationPresenter extends BasePresenter {

    void sendRegistrationInfoToServer(VolleyControler volleyControler , RegistrationModel model);
    interface RegistrationData
    {
        void onFailedToSaved(String msg);
        void onSuccessfulToSaved(String msg);
    }

}
