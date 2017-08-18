package com.itg.jobcardmanagement.common;

/**
 * Created by itg_Android on 8/7/2017.
 */

public class NetworkCall implements NetworkListener {
    private static final String BASE_URL = "http://103.229.24.44:8086";

    private static final NetworkCall ourInstance = new NetworkCall();

    private NetworkCall() {
    }

    public static NetworkCall getInstance() {
        return ourInstance;
    }

    public String checkLoginByUsername() {
        return BASE_URL + LOGIN_URL;
    }

    public String checkLoginByPassword() {
        return BASE_URL + USER_LOGIN;
    }

    /**
     * Registration Fail
     * {
     * "Message": "The request is invalid.",
     * "ModelState": {
     * "": [
     * "Name rajkumaddri@gmail.com is already taken."
     * ]
     * }
     * }
     * Successful
     * {
     * "status": "Registered Successfully",
     * "flag": true
     * }
     */
    public String register() {
        return BASE_URL + REGISTRATION;
    }


    /**
     * PARAM:
     * username:7eb8c366-4ba0-4e66-a02f-9543aa569f7e
     * grant_type:password
     * password:123456
     * <br> </br>
     * RESPONSE: <br>
     * {
     * "access_token": "",
     * "token_type": "bearer",
     * "expires_in": 1209599,
     * "userName": "raj@gmail.com",
     * ".issued": "Wed, 09 Aug 2017 11:07:11 GMT",
     * ".expires": "Wed, 23 Aug 2017 11:07:11 GMT"
     * }
     * <br />
     * Invalid Response:
     * <br />
     * {
     * "error": "invalid_grant",
     * "error_description": "The user name or password is incorrect."
     * }
     */

    public String verifyUser() {
        return BASE_URL + VERIFY;
    }
}
