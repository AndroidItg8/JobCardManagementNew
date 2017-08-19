package com.itg.jobcardmanagement.common;

/**
 * Created by me  itg_Android on 8/7/2017.
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


//    {
//        "User": {
//        "pkid": 0,
//                "userid": "e99b9059-e779-4359-afc8-ef798f406f8b",
//                "FirstName": "Raj",
//                "LastName": "Verma",
//                "username": null,
//                "address": "Manewada ",
//                "mobile": "5645646614",
//                "emailid": "Raj@gmail.com",
//                "mid": null,
//                "mdate": null,
//                "profilePic": null
//    },
//        "Vehicle": {
//        "pkid": 0,
//                "UserId": null,
//                "VehicleName": "Audi A4",
//                "VehicleNo": "51541",
//                "ChessesNo": null,
//                "series": "A4",
//                "model": "56464",
//                "VIN": "546",
//                "EngineNo": "546",
//                "Color": null,
//                "DealerCode": null,
//                "RegiNumber": "56",
//                "RCNumber": null,
//                "CurrentDate": null,
//                "mid": null,
//                "Mdate": null,
//                "cid": null
//    },
//        "servicing": {
//        "pkid": 0,
//                "UserId": null,
//                "VehicleId": null,
//                "ServicingDate": null,
//                "Servicingplace": null,
//                "servicingPerson": null,
//                "job_type": null,
//                "appointeddate": null,
//                "ariveddate": null,
//                "FOdometerereading": null,
//                "returnoldpar": null,
//                "washing": null,
//                "delivery": null,
//                "remark": null,
//                "customer_request": null,
//                "documentdatetime": null,
//                "TotalCharges": null
//    }
//    }

    public String checkVehicle() {
        return BASE_URL + VEHICLE_CHECK;
    }


    /***
     *
     * {
     "status": "Data Save Failed",
     "flag": false
     }
     **/
    public String userProfileSave() {
        return BASE_URL + USER_PROFILE;
    }

    /***
     *
     * {
     "carid": "sdf",
     "flag": false
     }
     **/
    public String vehicleSave() {
        return BASE_URL + VEHICLE_SAVE;
    }

    public String userAllDetails() {
        return BASE_URL + COMPLETE_DETAIL;
    }
}
