package com.itg.jobcardmanagement.registration.mvp.presenter;

import android.text.TextUtils;

import com.itg.jobcardmanagement.common.BaseWeakPresenter;
import com.itg.jobcardmanagement.registration.mvp.LoginRegMVP;
import com.itg.jobcardmanagement.registration.mvp.module.PasswordModuleImp;


public class  PasswordPresenterImp extends BaseWeakPresenter implements LoginRegMVP.PasswordPresenter, LoginRegMVP.PasswordListener {

    private final PasswordModuleImp model;

    public PasswordPresenterImp(LoginRegMVP.PasswordView o) {
        super(o);
        model=new PasswordModuleImp();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onNextButtonClicked() {
        boolean toValidate=false;

        if(hasView()){
            String pass=getPassView().getPassword();
            String username=getPassView().getUsername();
            if(TextUtils.isEmpty(pass)){
                getPassView().onPasswordEmpty();
                toValidate=true;
            }
            if(username==null){
                getPassView().onCallFail("username is null");
                toValidate=true;
            }
            if(!toValidate){
                getPassView().onProgressShow();
                model.onNextButtonSubmit(username,pass,this);
            }
        }
    }

    private LoginRegMVP.PasswordView getPassView(){
        return (LoginRegMVP.PasswordView) getView();
    }

    @Override
    public void onSuccess(Object object) {
        if(hasView()){
            getPassView().onPogressHide();
            getPassView().onSuccessful(object);
        }
    }

    @Override
    public void onFail(String message) {
        if(hasView()){
            getPassView().onPogressHide();
            getPassView().onCallFail(message);
        }
    }

    @Override
    public void onIncorrectPassword() {
        if(hasView()){
            getPassView().onPogressHide();
            getPassView().onPasswordInvalid();
        }
    }
}
