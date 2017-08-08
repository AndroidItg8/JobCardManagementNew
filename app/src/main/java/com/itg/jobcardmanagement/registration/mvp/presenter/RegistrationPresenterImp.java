package com.itg.jobcardmanagement.registration.mvp.presenter;

import com.itg.jobcardmanagement.common.VolleyControler;
import com.itg.jobcardmanagement.registration.model.RegistrationModel;
import com.itg.jobcardmanagement.registration.mvp.module.RegistrationModule;
import com.itg.jobcardmanagement.registration.mvp.view.RegistrationView;

/**
 * Created by Android itg 8 on 8/7/2017.
 */

public class RegistrationPresenterImp  implements  RegistrationPresenter,RegistrationPresenter.RegistrationData {


    private RegistrationView view;
     private RegistrationModule module;

    public RegistrationPresenterImp(RegistrationView view) {

        this.view = view;

    }

    @Override
    public void onProgressHide() {

    }

    @Override
    public void onProgressShow() {

    }

    @Override
    public void onNetworkAvailable() {

    }

    @Override
    public void onNoNetworkAvailable() {

    }

    @Override
    public void sendRegistrationInfoToServer(VolleyControler volleyControler, RegistrationModel model) {

    }

    @Override
    public void onFailedToSaved(String msg) {

    }

    @Override
    public void onSuccessfulToSaved(String msg) {

    }
}
