package com.itg.jobcardmanagement.registration.mvp;

/**
 * Created by me  itg_Android on 8/8/2017.
 */

public interface RegMVP {
    public interface RegView{
        void onPasswordNotMatch();
        void onPasswordConditionFail();
        void onPasswordEmpty();
        void onFirstnameEmpty();
        void onLastnameEmpty();
        void onNameContainingInteger(int field);
        void onRegistrationFail(String message);
        void onRegistrationSuccessful(Object... obj);
        String getPassword();
        String getFName();
        String getLName();
        String getCPassword();

        void onCpasswordEmpty();
    }

    public interface RegPresenter{
        void onResume();
        void onPause();
        void onDetachView();
        void onAttach();
        void onNextButtonClicked();

    }

    public interface RegModule{
        void onDestroy();
        void onNextButtonClicked(String fName, String lName, String password,String cPassword,RegListener listener);
    }

    public interface RegListener{
        void onSuccessfulReg(String token);
        void onRegistrationFail(String cause);
        void onNetworkCallFailed(String cause);

        void onUnkownError(String response);
    }

}
