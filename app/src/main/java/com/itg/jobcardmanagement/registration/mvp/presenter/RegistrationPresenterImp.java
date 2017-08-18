package com.itg.jobcardmanagement.registration.mvp.presenter;

import com.itg.jobcardmanagement.common.BaseWeakPresenter;
import com.itg.jobcardmanagement.registration.model.RegistrationModel;
import com.itg.jobcardmanagement.registration.mvp.LoginRegMVP;
import com.itg.jobcardmanagement.registration.mvp.module.RegistrationModuleImp;

/**
 * Created by Android itg 8 on 8/7/2017.
 */

public class RegistrationPresenterImp extends BaseWeakPresenter implements LoginRegMVP.RegistrationPresenter, LoginRegMVP.RegistrationListener {

    private final LoginRegMVP.RegistrationModule module;

    public RegistrationPresenterImp(LoginRegMVP.RegistrationView view) {
        super(view);

        module = new RegistrationModuleImp(this);
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
    public void onsendRegistrationInfoToServer(RegistrationModel model) {
        module.onRegistrationDetailsSentToServer(model);

    }

    @Override
    public void onSaveSuccessfully(String msg) {
        if (hasView()) {
            getRegView().onSaveRegistartionSuccesfully(msg);
        }

    }

    @Override
    public void onFailedToSave(String msg) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onNetworkFailed(String msg) {

    }

    @Override
    public void onNetworkAvaailable(String msg) {

    }

    @Override
    public void onUserNameNotMatch(String msg) {
        if (hasView()) {
            getRegView().onUserNotMatch(msg);
        }

    }

    @Override
    public void onUserNameMatch(String failed) {
        if (hasView()) {
            getRegView().onUserNameMatch(failed);
        }

    }

    private LoginRegMVP.RegistrationView getRegView() {
        return (LoginRegMVP.RegistrationView) getView();
    }
}
