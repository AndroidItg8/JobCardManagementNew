package com.itg.jobcardmanagement.registration.mvp;

import static android.R.attr.type;
import static com.itg.jobcardmanagement.registration.mvp.LoginPresenterImp.MOBILE;

/**
 * Created by itg_Android on 8/5/2017.
 */

public class LoginModuleImp implements LoginRegMVP.LoginModule {

    private static final String DUMMY_USERNAME = "9890410668";
    private LoginRegMVP.LoginListener listener;

    public LoginModuleImp(LoginRegMVP.LoginListener listener) {

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
        listener = null;
    }

    @Override
    public void onUsernameSubmit(String username, int type) {
        if (listener != null) {
            if (username.equalsIgnoreCase(DUMMY_USERNAME)
                    && type == MOBILE) {
                listener.onRegSuccess("Yey");
            } else {
                listener.onRegFail("Booo");
            }
        }
    }

    @Override
    public void onSignupClicked(String username) {
        if (listener != null) {
            if (username.equalsIgnoreCase(DUMMY_USERNAME)) {
                listener.onRegSuccess("Yey");
            } else {
                listener.onRegFail("Booo");
            }
        }
    }

    @Override
    public void onRegistrationClicked(Object registration) {

    }

    @Override
    public void onLoginClicked(String userID, String password) {

    }
}
