package com.itg.jobcardmanagement.registration.mvp;

import android.text.TextUtils;

import com.itg.jobcardmanagement.common.BaseWeakPresenter;

/**
 * Created by me  itg_Android on 8/8/2017.
 */

public class  RegPresenterImp extends BaseWeakPresenter implements RegMVP.RegPresenter, RegMVP.RegListener {


    private static final String REG_ALFA = "[a-zA-Z]+";
    private RegMVP.RegModule module;

    public RegPresenterImp(RegMVP.RegView o) {
        super(o);
        module = new RegModuleImp();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDetachView() {

    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onNextButtonClicked() {
        if (hasView()) {
            String fName = getRegView().getFName();
            String lName = getRegView().getLName();
            String pass = getRegView().getPassword();
            String cPass = getRegView().getCPassword();
            boolean toValidate = false;
            if (TextUtils.isEmpty(fName)) {
                getRegView().onFirstnameEmpty();
                toValidate = true;
            }
            if (TextUtils.isEmpty(lName)) {
                toValidate = true;
                getRegView().onLastnameEmpty();
            }
            if (TextUtils.isEmpty(pass)) {
                toValidate = true;
                getRegView().onPasswordEmpty();
            }
            if (TextUtils.isEmpty(cPass)) {
                toValidate = true;
                getRegView().onCpasswordEmpty();
            }
            if (pass != null && pass.length() < 6) {
                toValidate = true;
                getRegView().onPasswordConditionFail();
            }
            if (cPass != null && pass != null && !cPass.equals(pass)) {
                toValidate = true;
                getRegView().onPasswordNotMatch();
            }
            if (fName!=null && !fName.matches(REG_ALFA)) {
                toValidate = true;
                getRegView().onNameContainingInteger(1);
            }
            if (lName!=null && !lName.matches(REG_ALFA)) {
                toValidate = true;
                getRegView().onNameContainingInteger(2);
            }

            if (!toValidate) {
                module.onNextButtonClicked(fName, lName, pass, cPass, this);
            }
        }
    }


    private RegMVP.RegView getRegView() {
        return (RegMVP.RegView) getView();
    }

    @Override
    public void onSuccessfulReg(String token) {
        if(hasView()){
            getRegView().onRegistrationSuccessful(token);
        }
    }

    @Override
    public void onRegistrationFail(String cause) {
        if(hasView()){
            getRegView().onRegistrationFail("Web service: "+cause);
        }
    }

    @Override
    public void onNetworkCallFailed(String cause) {
        if (hasView()){
            getRegView().onRegistrationFail("Network:"+cause);
        }
    }

    @Override
    public void onUnkownError(String response) {
        if(hasView()){
            getRegView().onRegistrationFail("Response: "+response);
        }
    }
}
