package com.itg.jobcardmanagement.common;

/**
 * Created by itg_Android on 8/7/2017.
 */

public interface NetworkListener {
    String LOGIN_URL = "/api/CustomerApp/VerifyUser";
    String USER_LOGIN = "/Account/UserLogin";
    String REGISTRATION = "/api/CustomerApp/Register";
    String VERIFY = "/Token";
    String VEHICLE_CHECK ="/api/CustomerApp/CarAvailability";
    String USER_PROFILE="/api/CustomerApp/UserProfile";
    String VEHICLE_SAVE="/api/CustomerApp/VehicleSave";
}
