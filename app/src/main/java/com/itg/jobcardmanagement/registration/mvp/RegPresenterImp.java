package com.itg.jobcardmanagement.registration.mvp;

import android.text.TextUtils;

import com.itg.jobcardmanagement.common.BaseWeakPresenter;

/**
 * Created by itg_Android on 8/8/2017.
 */

public class RegPresenterImp extends BaseWeakPresenter implements RegMVP.RegPresenter {


    public RegPresenterImp(RegMVP.RegView o) {
        super(o);

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
        if(hasView()){
            String fName=getRegView().getFName();
            String lName=getRegView().getLName();
            String pass=getRegView().getPassword();
            String cPass=getRegView().getCPassword();
            boolean toValidate;
            if(TextUtils.isEmpty(fName)){
                getRegView().onFirstnameEmpty();
                toValidate=true;
            }
            if(TextUtils.isEmpty(lName)){
                toValidate=true;
                getRegView().onLastnameEmpty();
            }
            if(TextUtils.isEmpty(pass)) {
                toValidate=true;
                getRegView().onPasswordEmpty();
            }
            if(TextUtils.isEmpty(cPass)) {
                toValidate=true;
                getRegView().onCpasswordEmpty();
            }
            if(pass!=null && pass.length()<6){
                toValidate=true;
                getRegView().onPasswordConditionFail();
            }
            if(cPass!=null && pass!=null && !cPass.equals(pass)){
                toValidate=true;
                getRegView().onPasswordNotMatch();
            }


        }
    }


    private RegMVP.RegView getRegView(){
        return (RegMVP.RegView) getView();
    }
}
