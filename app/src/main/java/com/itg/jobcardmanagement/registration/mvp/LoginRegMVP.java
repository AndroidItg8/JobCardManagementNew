package com.itg.jobcardmanagement.registration.mvp;

import com.itg.jobcardmanagement.common.BaseModule;

/**
 * Created by itg_Android on 8/5/2017.
 */

public class LoginRegMVP {

    public interface LoginView{
        void onEmailEntered(String email);
        void onMobileNumberEntered(String mobileNo);
        void onEmailInvalid();
        void onMobileInvalid();
        void onUsernameFieldEmpty();
        void onVerificationFailed(String message);
        void onPasswordNotMatch();
        void onUserFound(String userId, String profilePicUrl);
        void showProgress();
        void hideProgress();

        String getUsername();
    }

   public interface LoginPresenter{
        void onUsernameSubmit(String username);
        void onSignupClicked(String username);
        void onRegistrationClicked(Object regModel);
        void onLoginClicked(String userId, String password);
        void onPause();
        void onResume();
        void onDestroy();
    }

    interface LoginModule extends BaseModule {
        void onUsernameSubmit(String username, int type);
        void onSignupClicked(String username);
        void onRegistrationClicked(Object registration);
        void onLoginClicked(String userID,String password);
    }

    public interface LoginListener{
        void onUserAvaiable(String userId, String profilePicUrl);
        void onUserFail(String message);
        void onError(String err);
        void onRegFail(String message);
        void onRegSuccess(Object module);
    }

    public interface PasswordView{
        void onPasswordEmpty();
        void onPasswordInvalid();
        void onProgressShow();
        void onPogressHide();
        String getPassword();
        void onSuccessful(Object o);

        void onCallFail(String message);

        String getUsername();
    }

    public interface PasswordPresenter{
        void onResume();
        void onPause();
        void onNextButtonClicked();
    }

    public interface PasswordModule{
        void onDestroy();
        void onNextButtonSubmit(String username, String pass,PasswordListener listener);
    }

    public interface PasswordListener{
        void onSuccess(Object object);
        void onFail(String message);
        void onIncorrectPassword();
    }

}
