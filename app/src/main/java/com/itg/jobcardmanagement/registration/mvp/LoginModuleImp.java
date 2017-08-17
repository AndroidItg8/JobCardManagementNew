package com.itg.jobcardmanagement.registration.mvp;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.common.MyApplication;
import com.itg.jobcardmanagement.common.NetworkCall;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by itg_Android on 8/5/2017.
 */

public class LoginModuleImp implements LoginRegMVP.LoginModule {

    private static final String DUMMY_USERNAME = "9890410668";
    private static final String PROFILE_PIC = "Profiepic";
    private static final String STATUS = "status";
    private static final String FLAG = "flag";
    private LoginRegMVP.LoginListener listener;

    public LoginModuleImp(LoginRegMVP.LoginListener listener) {
        this.listener = listener;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        listener = null;
    }

    @Override
    public void onUsernameSubmit(final String username, final int type) {
        if (listener != null) {
//            if (username.equalsIgnoreCase(DUMMY_USERNAME)
//                    && type == MOBILE) {
//                listener.onRegSuccess("Yey");
//            } else {
//                listener.onRegFail("Booo");
//            }

//            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, NetworkCall.getInstance().checkLoginByUsername(),null, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            }) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    return super.getParams();
//                }
//            };
//        }
            Map<String, String> params = new HashMap<String, String>();
            params.put("Userid", username);
//            params.put("usernametype", String.valueOf(type));
            JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, NetworkCall.getInstance().checkLoginByUsername(), new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // response
                            VolleyLog.d("Response", response.toString());
                            try {

                                if (response.getBoolean(FLAG)) {
                                    listener.onUserAvaiable(response.getString(STATUS), response.getString(PROFILE_PIC));

                                } else {
                                    listener.onRegFail(username);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            error.printStackTrace();
                            listener.onError(error.getMessage());
                            Log.d("Error.Response", error.toString());
                        }
                    }
            );
            MyApplication.getInstance().addToRequestQueue(postRequest);
        }
    }


    public void onGmailSigninClicked(String tokenId){

    }

    @Override
    public void onSignupClicked(String username) {
        if (listener != null) {
            if (username.equalsIgnoreCase(DUMMY_USERNAME)) {
                listener.onRegSuccess("Yey");
            } else {
                listener.onRegFail("Booo");
            }
        }
    }

    @Override
    public void onRegistrationClicked(Object registration) {

    }

    @Override
    public void onLoginClicked(String userID, String password) {

    }
}
