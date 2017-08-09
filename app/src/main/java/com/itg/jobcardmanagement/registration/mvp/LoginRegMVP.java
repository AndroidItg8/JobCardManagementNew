package com.itg.jobcardmanagement.registration.mvp;

import android.widget.EditText;

import com.itg.jobcardmanagement.common.BaseModule;
import com.itg.jobcardmanagement.common.common_interface.BasePresenter;
import com.itg.jobcardmanagement.common.common_interface.BaseView;
import com.itg.jobcardmanagement.registration.model.RegistrationModel;

/**
 * Created by itg_Android on 8/5/2017.
 */

public class LoginRegMVP {

    public interface LoginView {
        void onEmailEntered(String email);

        void onMobileNumberEntered(String mobileNo);

        void onEmailInvalid();

        void onMobileInvalid();

        void onVerificationFailed(String message);

        void onPasswordNotMatch();

        void onUserFound(String userId, String profilePicUrl);

        void showProgress();

        void hideProgress();
    }

    public interface LoginPresenter {
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

        void onLoginClicked(String userID, String password);
    }

    public interface LoginListener {
        void onUserAvaiable(String userId, String profilePicUrl);
        void onUserFail(String message);
        void onError(String err);
        void onRegFail(String message);
        void onRegSuccess(Object module);
    }

    public interface RegistrationView extends BaseView {
        void onSendRegistrationDetailsToServer(RegistrationModel model);
        void onFeildError(EditText... editTexts);
        void onSaveRegistartionSuccesfully(String msg);
        void onUserNotMatch(String msg);
        void onUserNameMatch(String failed);
    }
     public interface RegistrationListener
     {
         void onSaveSuccessfully( String msg);
         void onFailedToSave(String msg);
         void onError(String msg);
         void onNetworkFailed(String msg);
         void onNetworkAvaailable(String msg);
          void onUserNameNotMatch(String msg);
         void onUserNameMatch(String failed);
     }




    public interface RegistrationPresenter extends BasePresenter {
        void onsendRegistrationInfoToServer(RegistrationModel model);
    }

    public  interface  RegistrationModule extends BaseModule
    {
        void onRegistrationDetailsSentToServer(RegistrationModel model);
    }
}
