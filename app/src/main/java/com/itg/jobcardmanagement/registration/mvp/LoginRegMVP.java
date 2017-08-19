package com.itg.jobcardmanagement.registration.mvp;

import android.widget.EditText;

import com.itg.jobcardmanagement.common.BaseModule;
import com.itg.jobcardmanagement.common.common_interface.BasePresenter;
import com.itg.jobcardmanagement.common.common_interface.BaseView;
import com.itg.jobcardmanagement.registration.model.RegistrationModel;
import com.itg.jobcardmanagement.registration.model.User;
import com.itg.jobcardmanagement.registration.model.UserVehicleDetailModel;
import com.itg.jobcardmanagement.registration.model.Vehicle;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by me  itg_Android on 8/5/2017.
 */

public class LoginRegMVP {

    public interface LoginView {
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

    public interface PasswordView {
        void onPasswordEmpty();

        void onPasswordInvalid();

        void onProgressShow();

        void onPogressHide();

        String getPassword();

        void onSuccessful(Object o);

        void onCallFail(String message);

        String getUsername();
    }

    public interface PasswordPresenter {
        void onResume();

        void onPause();

        void onNextButtonClicked();
    }

    public interface PasswordModule {
        void onDestroy();

        void onNextButtonSubmit(String username, String pass, PasswordListener listener);
    }

    public interface PasswordListener {
        void onSuccess(Object object);

        void onFail(String message);

        void onIncorrectPassword();
    }

    public interface RegistrationView extends BaseView {
        void onSendRegistrationDetailsToServer(RegistrationModel model);

        void onFeildError(EditText... editTexts);

        void onSaveRegistartionSuccesfully(String msg);

        void onUserNotMatch(Object msg);

        void onUserNameMatch(Object failed);

        String getRegNumber();

        String getChesisNumber();

        void onProfileSuccessfullySaved();

        void onInvalidRegNumber(String err);

        void onInvalidChasisNo(String err);

        void onUserNotAvailable(String s);

        void startFabProgress();

        void hideFabProgress();

        void setShowcaseRegAndChesis(String regNumber, String chasisNumber);

        void onShowcaseShow();

        void onHideKeyboard();

        void startGettingUserProfileData();

        void startGettingVehicleData();

        void finishActivityRegComplete();

        void onVehicleStorageFail();

        void onVehicleStorageSuccess(String carid);

        void showProfileDownloadProgress();
        void hideProfileDownloadProgress();
    }

    public interface RegistrationListener {
        void onUserSaveSuccessfully(String msg);

        void onFailedToSave(String msg);

        void onError(String msg);

        void onNetworkFailed(String msg);

        void onNetworkAvaailable(String msg);

        void onUserNameNotMatch(Object msg);

        void onUserNameMatch(Object failed);

        void onUserNotAvailable(String s);

        void onUserSaveFailed();

        void onVehicleStoreFail();

        void onVehicleStoredSuccess(String carid);

        void onProfileDownloadComplete(JSONObject response);

        void onProfileDownloadFailed(Object response);

        void onNoVehicleRegisteredWithUser();

        void onVehicleAlreadyRegisteredWithUser();
    }


    public interface RegistrationPresenter extends BasePresenter {
        void onsendRegistrationInfoToServer(RegistrationModel model);

        void onUserProfileAvail(User user);

        void onVehicleDetailsAvailable(Vehicle vehicle);

        void checkUsersDetails();
    }

    public interface RegistrationModule extends BaseModule {
        void onRegistrationDetailsSentToServer(RegistrationModel model);


        void checkIfVehicleAvailable(String regNumber, String chasisNumber);

        void onProfileSendToServer(HashMap<String, String> hashParam);

        void onVehicleSendToServer(HashMap<String,String> hashParam);

        void downloadProfileDetails();


        void storeProfileVehicleAndSevicingInfo(UserVehicleDetailModel model);
    }
}
