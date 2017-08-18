package com.itg.jobcardmanagement.registration.mvp;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.itg.jobcardmanagement.common.CommonMethod;
import com.itg.jobcardmanagement.common.Logger;
import com.itg.jobcardmanagement.common.MyApplication;
import com.itg.jobcardmanagement.common.NetworkCall;
import com.itg.jobcardmanagement.common.Prefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by swapnilmeshram on 09/08/17.
 */

public class RegModuleImp implements RegMVP.RegModule {

    private static final String FLAG = "flag";
    private static final String MESSAGE = "Message";
    private static final String TOKEN = "token";

    @Override
    public void onDestroy() {
        MyApplication.getInstance().cancelPendingRequests(CommonMethod.REG_REQ);
    }

    @Override
    public void onNextButtonClicked(String fName, String lName, String password, String cPassword, final RegMVP.RegListener listener) {
        final HashMap<String, String> param = new HashMap<>();
//        UserName:9890410668
//        Password:123456
//        ConfirmPassword:123456
//        fname:rajkumar
//        lname:Verma
        param.put("UserName", Prefs.getString(CommonMethod.USER_EMAIL_OR_PHONE_NUMBER,null));
        param.put("Password",password);
        param.put("ConfirmPassword",cPassword);
        param.put("fname",fName);
        param.put("lname",lName);
        StringRequest request = new StringRequest(Request.Method.POST, NetworkCall.getInstance().register(), new Response.Listener<String>() {
            @Override
            public void onResponse(String res) {
                if (res != null) {
                    try {
                        JSONObject response=new JSONObject(res);
                        if (response.has(FLAG)) {
                            listener.onSuccessfulReg(response.getString(TOKEN));
                        } else if (response.has(MESSAGE)) {
                            listener.onNetworkCallFailed(response.getString(MESSAGE));
                        } else {
                            listener.onUnkownError(new Gson().toJson(response));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        listener.onNetworkCallFailed("err");
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                Logger.i(res);
                                // Now you can use any deserializer to make sense of data
                                JSONObject obj = new JSONObject(res);
                                Logger.i(obj.toString());
                            } catch (UnsupportedEncodingException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                            } catch (JSONException e2) {
                                // returned data is not JSONObject?
                                e2.printStackTrace();
                            }
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return param;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        MyApplication.getInstance().addToRequestQueue(request, CommonMethod.REG_REQ);
    }
}
