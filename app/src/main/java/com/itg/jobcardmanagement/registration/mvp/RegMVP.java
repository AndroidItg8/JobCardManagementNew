package com.itg.jobcardmanagement.registration.mvp;

/**
 * Created by itg_Android on 8/8/2017.
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
}
