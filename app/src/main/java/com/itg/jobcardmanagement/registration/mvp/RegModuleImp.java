package com.itg.jobcardmanagement.registration.mvp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.itg.jobcardmanagement.common.CommonMethod;
import com.itg.jobcardmanagement.common.MyApplication;
import com.itg.jobcardmanagement.common.NetworkCall;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by swapnilmeshram on 09/08/17.
 */

public class RegModuleImp implements RegMVP.RegModule {

    private static final String FLAG = "flag";
    private static final String MESSAGE = "Message";

    @Override
    public void onDestroy() {
        MyApplication.getInstance().cancelPendingRequests(CommonMethod.REG_REQ);
    }

    @Override
    public void onNextButtonClicked(String fName, String lName, String password, String cPassword, final RegMVP.RegListener listener) {
        HashMap<String, String> param = new HashMap<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, NetworkCall.getInstance().register(), new JSONObject(param), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    try {
                        if (response.has(FLAG)) {
                            listener.onSuccessfulReg(null);
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
                        listener.onNetworkCallFailed(error.getCause().getMessage());
                    }
                });

        MyApplication.getInstance().addToRequestQueue(request, CommonMethod.REG_REQ);
    }
}
