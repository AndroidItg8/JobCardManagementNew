package com.itg.jobcardmanagement.registration.mvp;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.itg.jobcardmanagement.common.MyApplication;
import com.itg.jobcardmanagement.common.NetworkCall;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by itg_Android on 8/5/2017.
 */

public class LoginModuleImp implements LoginRegMVP.LoginModule {

    private static final String DUMMY_USERNAME = "9890410668";
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
            params.put("Emailid",username);
            params.put("usernametype", String.valueOf(type));
            JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, NetworkCall.getInstance().checkLoginByUsername(),new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // response
                            
                            VolleyLog.d("Response", response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            error.printStackTrace();
                            Log.d("Error.Response", error.getMessage());
                        }
                    }
            ) ;
            MyApplication.getInstance().addToRequestQueue(postRequest);
        }
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
