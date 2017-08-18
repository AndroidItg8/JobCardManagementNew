package com.itg.jobcardmanagement.registration.mvp;

import com.itg.jobcardmanagement.common.BaseWeakPresenter;
import com.itg.jobcardmanagement.common.CommonMethod;
import com.itg.jobcardmanagement.common.Prefs;

/**
 * Created by itg_Android on 8/5/2017.
 */

public class LoginPresenterImp extends BaseWeakPresenter implements LoginRegMVP.LoginListener, LoginRegMVP.LoginPresenter {


    public static final int EMAIL = 1;
    public static final int MOBILE = 2;
    private LoginModuleImp module;

    public LoginPresenterImp(LoginRegMVP.LoginView view) {
        super(view);
        try {
            module = new LoginModuleImp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUsernameSubmit(String username) {
        if (hasView()) {
            int type = checkTypeofUsername(username);
            if (type > 0) {
                getLoginView().showProgress();
                Prefs.putInt(CommonMethod.USERNAME_TYPE, type == EMAIL ? EMAIL : MOBILE);
                Prefs.putString(CommonMethod.USERNAME_INSERTED,username);
                module.onUsernameSubmit(username, type);
            }
        }
    }

    private int checkTypeofUsername(String username) {
        if (username.isEmpty() && hasView()) {
            getLoginView().onUsernameFieldEmpty();
        }
        int type = -1;
        if (isEmailValid(username)) {
            type = EMAIL;
            getLoginView().onEmailEntered(username);
        } else if (isValidPhoneNumber(username)) {
            if (username.length() != 10) {
                getLoginView().onMobileInvalid();
            } else {
                type = MOBILE;
                getLoginView().onMobileNumberEntered(username);
            }
        } else {
            getLoginView().onEmailInvalid();
        }
        return type;
    }

    private boolean isValidPhoneNumber(String mobile) {
        String regEx = "[0-9]+";
        return mobile.matches(regEx);
    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onSignupClicked(String username) {

    }

    @Override
    public void onRegistrationClicked(Object regModel) {

    }

    @Override
    public void onLoginClicked(String userId, String password) {

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
    public void onUserAvaiable(String userId, String profilePicUrl) {
        if (hasView()) {
            getLoginView().hideProgress();
            getLoginView().onUserFound(userId, profilePicUrl);
        }
    }

    @Override
    public void onUserFail(String message) {
        if (hasView()) {
            getLoginView().hideProgress();
        }
    }

    @Override
    public void onError(String err) {
        if (hasView()) {
            getLoginView().hideProgress();
        }
    }

    @Override
    public void onRegFail(String message) {
        if (hasView()) {
            getLoginView().onVerificationFailed(message);
            if (hasView()) {
                getLoginView().hideProgress();
            }
        }
    }

    @Override
    public void onRegSuccess(Object module) {
        if (hasView()) {
            getLoginView().onUserFound("1", "www");
        }
    }

    private LoginRegMVP.LoginView getLoginView() {
        return (LoginRegMVP.LoginView) getView();
    }
}
