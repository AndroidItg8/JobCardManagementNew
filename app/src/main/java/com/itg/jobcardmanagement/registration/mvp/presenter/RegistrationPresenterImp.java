package com.itg.jobcardmanagement.registration.mvp.presenter;

import android.text.TextUtils;

import com.itg.jobcardmanagement.common.BaseWeakPresenter;
import com.itg.jobcardmanagement.registration.model.RegistrationModel;
import com.itg.jobcardmanagement.registration.model.User;
import com.itg.jobcardmanagement.registration.model.Vehicle;
import com.itg.jobcardmanagement.registration.mvp.LoginRegMVP;
import com.itg.jobcardmanagement.registration.mvp.module.RegistrationModuleImp;

import java.util.HashMap;

/**
 * Created by Android itg 8 on 8/7/2017.
 */

public class RegistrationPresenterImp extends BaseWeakPresenter implements LoginRegMVP.RegistrationPresenter, LoginRegMVP.RegistrationListener {

    private static final int FINISH_REGISTRATION = 4;
    private static final int REG_CHECK = 1;
    private static final int PROFILE_CHECK = 2;
    private static final int VEHICLE_CHECK = 3;
    private final LoginRegMVP.RegistrationModule module;
    private int typeOfFabClicked = -1;

    public RegistrationPresenterImp(LoginRegMVP.RegistrationView view) {
        super(view);
        typeOfFabClicked = REG_CHECK;
        module = new RegistrationModuleImp(this);
    }

    @Override
    public void onProgressHide() {


    }

    @Override
    public void onProgressShow() {

    }

    @Override
    public void onNetworkAvailable() {

    }

    @Override
    public void onNoNetworkAvailable() {

    }


    @Override
    public void onsendRegistrationInfoToServer(RegistrationModel model) {
        boolean toValidate = false;
        if (hasView() && typeOfFabClicked == REG_CHECK) {
            String regNumber = getRegView().getRegNumber();
            String chasisNumber = getRegView().getChesisNumber();
            if (TextUtils.isEmpty(regNumber)) {
                getRegView().onInvalidRegNumber("Please fill Registration number");
                toValidate = true;
            }
            if (TextUtils.isEmpty(chasisNumber)) {
                getRegView().onInvalidChasisNo("Please fill chassis number");
                toValidate = true;
            }

            if (!toValidate) {
                getRegView().onHideKeyboard();
                getRegView().onInvalidChasisNo(null);
                getRegView().onInvalidRegNumber(null);
                getRegView().startFabProgress();
                getRegView().setShowcaseRegAndChesis(regNumber, chasisNumber);
                module.checkIfVehicleAvailable(regNumber, chasisNumber);
            }
        } else if (hasView() && typeOfFabClicked == PROFILE_CHECK) {
            getRegView().startGettingUserProfileData();
        } else if (hasView() && typeOfFabClicked == VEHICLE_CHECK) {
            getRegView().startGettingVehicleData();
        } else if (hasView() && typeOfFabClicked == FINISH_REGISTRATION) {
            getRegView().finishActivityRegComplete();
        }
    }


    @Override
    public void onUserProfileAvail(User user) {
        if (user != null && hasView()) {
            getRegView().startFabProgress();
            module.onProfileSendToServer(createHashParam(user));

        }
    }


    @Override
    public void onVehicleDetailsAvailable(Vehicle vehicle) {
        if (vehicle != null && hasView()) {
            getRegView().startFabProgress();
            module.onVehicleSendToServer(createHashParam(vehicle));
        }
    }

    private HashMap<String, String> createHashParam(Vehicle vehicle) {
        HashMap<String, String> param = new HashMap<>();
        param.put("VehicleNo", vehicle.getVehicleNo());
        param.put("ChessesNo", vehicle.getChessesNo());
        param.put("Color", vehicle.getColor());
        param.put("DealerCode", vehicle.getDealerCode());
        param.put("series", vehicle.getSeries());
        param.put("model", vehicle.getModel());
        param.put("VIN", vehicle.getVIN());
        if(vehicle.getPkid()>0){
            param.put("pkid", String.valueOf(vehicle.getPkid()));
        }
        return param;
    }

    private HashMap<String, String> createHashParam(User user) {
//        FirstName
//        LastName
//        address
//        mobile
//        emailid
//        profilePic
        HashMap<String, String> param = new HashMap<>();
        param.put("FirstName", user.getFirstName());
        param.put("LastName", user.getLastName());
        param.put("address", user.getAddress());
        param.put("mobile", user.getMobile());
        param.put("emailid", user.getEmailid());
        return param;
    }

    @Override
    public void onUserSaveSuccessfully(String msg) {
        if (hasView()) {
            getRegView().hideFabProgress();
            getRegView().onProfileSuccessfullySaved();
            typeOfFabClicked = VEHICLE_CHECK;
        }

    }

    @Override
    public void onFailedToSave(String msg) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onNetworkFailed(String msg) {
        if (hasView()) {
            getRegView().hideFabProgress();
            getRegView().onNoNetworkAvailable();
        }
    }

    @Override
    public void onNetworkAvaailable(String msg) {

    }

    @Override
    public void onUserNameNotMatch(Object msg) {
        if (hasView()) {
            getRegView().hideFabProgress();
            getRegView().onUserNotMatch(msg);
            getRegView().onShowcaseShow();
            typeOfFabClicked = PROFILE_CHECK;

        }

    }

    @Override
    public void onUserNameMatch(Object response) {
        if (hasView()) {
            getRegView().hideFabProgress();
            getRegView().onUserNameMatch(response);
            getRegView().onShowcaseShow();
            typeOfFabClicked = PROFILE_CHECK;

        }

    }

    @Override
    public void onUserNotAvailable(String s) {
        if (hasView()) {
            getRegView().hideFabProgress();
            getRegView().onUserNotAvailable(s);
            getRegView().onShowcaseShow();
            typeOfFabClicked = PROFILE_CHECK;

        }
    }

    @Override
    public void onUserSaveFailed() {
        if (hasView()) {
            getRegView().hideFabProgress();
        }
    }

    private LoginRegMVP.RegistrationView getRegView() {
        return (LoginRegMVP.RegistrationView) getView();
    }

    @Override
    public void onVehicleStoreFail() {
        if (hasView()) {
            getRegView().hideFabProgress();
            getRegView().onVehicleStorageFail();
        }
    }

    @Override
    public void onVehicleStoredSuccess(String carid) {
        if (hasView()) {
            getRegView().hideFabProgress();
            getRegView().onVehicleStorageSuccess(carid);
            typeOfFabClicked = FINISH_REGISTRATION;

        }
    }
}
