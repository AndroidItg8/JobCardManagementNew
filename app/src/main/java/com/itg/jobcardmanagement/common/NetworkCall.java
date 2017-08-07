package com.itg.jobcardmanagement.common;

/**
 * Created by itg_Android on 8/7/2017.
 */

public class NetworkCall implements NetworkListener {
    private static final String BASE_URL="http://103.229.24.44:8086";

    private static final NetworkCall ourInstance = new NetworkCall();

    public static NetworkCall getInstance() {
        return ourInstance;
    }

    private NetworkCall() {
    }

    public String checkLoginByUsername(){
        return BASE_URL+LOGIN_URL;
    }

    public String checkLoginByPassword(){
        return BASE_URL+USER_LOGIN;
    }
}
