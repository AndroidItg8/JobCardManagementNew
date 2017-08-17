package com.itg.jobcardmanagement.registration.mvp.module;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.itg.jobcardmanagement.common.Logger;
import com.itg.jobcardmanagement.common.MyApplication;
import com.itg.jobcardmanagement.common.NetworkCall;
import com.itg.jobcardmanagement.registration.mvp.LoginRegMVP;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;

/**
 * Created by itg_Android on 8/9/2017.
 */

public class PasswordModuleImp implements LoginRegMVP.PasswordModule {
    private static final String PASS = PasswordModuleImp.class.getSimpleName();

    @Override
    public void onDestroy() {
        MyApplication.getInstance().cancelPendingRequests(PASS);
    }

    @Override
    public void onNextButtonSubmit(String username, String pass, final LoginRegMVP.PasswordListener listener) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("grant_type", "password");
        map.put("password", pass);
        Logger.i(new JSONObject(map).toString());
//        JsonObjectRequest request = new JsonObjectRequest(POST, NetworkCall.getInstance().verifyUser(), new JSONObject(map), new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                if (response != null) {
//                    try {
//
//                        if (response.has("access_token")) {
//                            listener.onSuccess(response.getString("access_token"));
//                        }else if(response.has("error")){
//                            listener.onIncorrectPassword();
//                        }else {
//                            listener.onFail(new Gson().toJson(response));
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        listener.onFail(e.getMessage());
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                listener.onFail(error.getMessage());
//            }
//        }
//        ){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/x-www-form-urlencoded");
//                return params;
//            }
//        };
    StringRequest request = new StringRequest(POST, NetworkCall.getInstance().verifyUser(), new Response.Listener<String>() {
            @Override
            public void onResponse(String res) {
                if (res != null) {
                    try {
                        Logger.i(res);
                        JSONObject response=new JSONObject(res);
                        if (response.has("access_token")) {
                            listener.onSuccess(response.getString("access_token"));
                        }else if(response.has("error")){
                            listener.onIncorrectPassword();
                        }else {
                            listener.onFail(new Gson().toJson(response));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        listener.onFail(e.getMessage());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                listener.onFail(error.getMessage());

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
        }
        ){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/x-www-form-urlencoded");
//                return params;
//            }

        @Override
        public String getBodyContentType() {
            return "application/x-www-form-urlencoded; charset=UTF-8";
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
    };

        MyApplication.getInstance().addToRequestQueue(request,PASS);
    }
}
