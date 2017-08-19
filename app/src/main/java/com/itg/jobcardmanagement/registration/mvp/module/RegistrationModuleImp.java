package com.itg.jobcardmanagement.registration.mvp.module;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.itg.jobcardmanagement.common.CommonMethod;
import com.itg.jobcardmanagement.common.Logger;
import com.itg.jobcardmanagement.common.MyApplication;
import com.itg.jobcardmanagement.common.NetworkCall;
import com.itg.jobcardmanagement.common.NetworkListener;
import com.itg.jobcardmanagement.common.Prefs;
import com.itg.jobcardmanagement.registration.model.RegistrationModel;
import com.itg.jobcardmanagement.registration.model.User;
import com.itg.jobcardmanagement.registration.model.UserVehicleDetailModel;
import com.itg.jobcardmanagement.registration.model.Vehicle;
import com.itg.jobcardmanagement.registration.mvp.LoginRegMVP;
import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.itg.jobcardmanagement.common.CommonMethod.FLAG;
import static com.itg.jobcardmanagement.common.NetworkListener.USER_PROFILE;
import static com.itg.jobcardmanagement.common.NetworkListener.VEHICLE_SAVE;
import static com.itg.jobcardmanagement.registration.mvp.LoginPresenterImp.EMAIL;
import static com.itg.jobcardmanagement.registration.mvp.LoginPresenterImp.MOBILE;

public class RegistrationModuleImp implements LoginRegMVP.RegistrationModule {


    private static final String VEHICLE_CHECK = RegistrationModuleImp.class.getName();
    private static final int VEHICLE_AVAIL = 1;
    private static final int VEHICLE_MISSED = 2;
    private final LoginRegMVP.RegistrationListener listener;

    public RegistrationModuleImp(LoginRegMVP.RegistrationListener listener) {
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

    }

    @Override
    public void onRegistrationDetailsSentToServer(RegistrationModel model) {
//         listener.onUserSaveSuccessfully("Sucess");
        listener.onUserNameNotMatch("FAiled");
        //  listener.onUserNameMatch("Failed");
    }

    @Override
    public void checkIfVehicleAvailable(String regNumber, String chasisNumber) {
        final HashMap<String, String> param = new HashMap<>();
//        UserName:9890410668
//        Password:123456
//        ConfirmPassword:123456
//        fname:rajkumar
//        lname:Verma
        param.put("ChessesNo", chasisNumber);
        param.put("VehicleNo", regNumber);
        StringRequest request = new StringRequest(Request.Method.POST, NetworkCall.getInstance().checkVehicle(), new Response.Listener<String>() {
            @Override
            public void onResponse(String res) {
                if (res != null) {
                    try {
                        JSONObject response = new JSONObject(res);
                        if (response.has(FLAG)) {
                            listener.onUserNotAvailable("");
                        } else if (response.has("User")) {
                            JSONObject user = response.getJSONObject("User");
                            if (Prefs.getInt(CommonMethod.USERNAME_TYPE, -1) == EMAIL) {
                                if (user != null) {
                                    String email = user.getString("emailid");
                                    if (Prefs.getString(CommonMethod.USERNAME_INSERTED, "").equalsIgnoreCase(email)) {
                                        listener.onUserNameMatch(response);
                                    } else {
                                        listener.onUserNameNotMatch(response);
                                    }
                                } else {
                                    Logger.i("user null");
                                }
                            } else if (Prefs.getInt(CommonMethod.USERNAME_TYPE, -1) == MOBILE) {
                                if (user != null) {
                                    String email = user.getString("mobile");
                                    if (Prefs.getString(CommonMethod.USERNAME_INSERTED, "").equalsIgnoreCase(email)) {
                                        listener.onUserNameMatch(response);
                                    } else {
                                        listener.onUserNameNotMatch(response);
                                    }
                                } else {
                                    Logger.i("user null");
                                }
                            }
                        } else {
                            listener.onError(new Gson().toJson(response));
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
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                Logger.i(res);
                                // Now you can use any deserializer to make sense of data
                                JSONObject obj = new JSONObject(res);
                                Logger.i(obj.toString());
                                listener.onNetworkFailed("error");
                            } catch (UnsupportedEncodingException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                                listener.onNetworkFailed("error");
                            } catch (JSONException e2) {
                                // returned data is not JSONObject?
                                e2.printStackTrace();
                                listener.onNetworkFailed("error");
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

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramsHeader = new HashMap<String, String>();
                paramsHeader.put("Authorization", "bearer " + Prefs.getString(CommonMethod.TOKEN, ""));
                return paramsHeader;
            }
        };

        MyApplication.getInstance().addToRequestQueue(request, VEHICLE_CHECK);

    }


    @Override
    public void onProfileSendToServer(final HashMap<String, String> param) {
        StringRequest request = new StringRequest(Request.Method.POST, NetworkCall.getInstance().userProfileSave(), new Response.Listener<String>() {
            @Override
            public void onResponse(String res) {
                if (res != null) {
                    try {
                        JSONObject response = new JSONObject(res);
                        if(response.has(FLAG)){
                            if(response.getBoolean(FLAG)){
                                listener.onUserSaveSuccessfully("");
                            }else {
                                listener.onUserSaveFailed();
                            }
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
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                Logger.i(res);
                                // Now you can use any deserializer to make sense of data
                                JSONObject obj = new JSONObject(res);
                                Logger.i(obj.toString());
                                listener.onNetworkFailed("error");
                            } catch (UnsupportedEncodingException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                                listener.onNetworkFailed("error");
                            } catch (JSONException e2) {
                                // returned data is not JSONObject?
                                e2.printStackTrace();
                                listener.onNetworkFailed("error");
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

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramsHeader = new HashMap<String, String>();
                paramsHeader.put("Authorization", "bearer " + Prefs.getString(CommonMethod.TOKEN,""));
                return paramsHeader;
            }
        };

        MyApplication.getInstance().addToRequestQueue(request, USER_PROFILE);

    }

    @Override
    public void onVehicleSendToServer(final HashMap<String, String> param) {
        StringRequest request = new StringRequest(Request.Method.POST, NetworkCall.getInstance().vehicleSave(), new Response.Listener<String>() {
            @Override
            public void onResponse(String res) {
                if (res != null) {
                    try {
                        Logger.i(res);
                        JSONObject response = new JSONObject(res);
                        if(response.has(FLAG)){
                            if(response.getBoolean(FLAG)){
                                listener.onVehicleStoredSuccess(response.getString("carid"));
                            }else {
                                listener.onVehicleStoreFail();
                            }
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
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                Logger.i(res);
                                // Now you can use any deserializer to make sense of data
                                JSONObject obj = new JSONObject(res);
                                Logger.i(obj.toString());
                                listener.onNetworkFailed("error");
                            } catch (UnsupportedEncodingException | JSONException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                                listener.onNetworkFailed("error");
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

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramsHeader = new HashMap<String, String>();
                paramsHeader.put("Authorization", "bearer " + Prefs.getString(CommonMethod.TOKEN,""));
                return paramsHeader;
            }
        };

        MyApplication.getInstance().addToRequestQueue(request, VEHICLE_SAVE);

    }


    @Override
    public void downloadProfileDetails() {
        StringRequest request = new StringRequest(Request.Method.GET, NetworkCall.getInstance().userAllDetails(), new Response.Listener<String>() {
            @Override
            public void onResponse(String res) {
                if (res != null) {
                    try {
                        Logger.i(res);
                        JSONObject response = new JSONObject(res);
                        if(response.has("User")){
                            listener.onProfileDownloadComplete(response);
                        }else {
                            listener.onProfileDownloadFailed(response);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        listener.onProfileDownloadFailed(e);
                    }

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                Logger.i(res);
                                // Now you can use any deserializer to make sense of data
                                JSONObject obj = new JSONObject(res);
                                Logger.i(obj.toString());
                                listener.onNetworkFailed("error");
                            } catch (UnsupportedEncodingException | JSONException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                                listener.onNetworkFailed("error");
                            }
                        }
                    }
                }) {

//
//            @Override
//            public String getBodyContentType() {
//                return "application/x-www-form-urlencoded; charset=UTF-8";
//            }
//
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramsHeader = new HashMap<String, String>();
                paramsHeader.put("Authorization", "bearer " + Prefs.getString(CommonMethod.TOKEN,""));
                return paramsHeader;
            }
        };

        MyApplication.getInstance().addToRequestQueue(request, NetworkListener.COMPLETE_DETAIL);
    }

    @Override
    public void storeProfileVehicleAndSevicingInfo(final UserVehicleDetailModel model) {




        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {

                List<User> users= SugarRecord.listAll(User.class);
                if(users!=null){
                    if(users.size()>0){
                        SugarRecord.deleteAll(User.class);
                    }
                }
                model.getUser().save();

                List<Vehicle> vehicles=model.getVehicle();
                if(vehicles!=null && vehicles.size()>0){
                    SugarRecord.deleteAll(Vehicle.class);
                    SugarRecord.saveInTx(vehicles);
                    e.onNext(VEHICLE_AVAIL);
                }else {
                    e.onNext(VEHICLE_MISSED);
                    listener.onNoVehicleRegisteredWithUser();
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Dis posable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        int type= (int) o;
                        if(type==VEHICLE_AVAIL)
                            listener.onVehicleAlreadyRegisteredWithUser();
                        else if(type==VEHICLE_MISSED)
                            listener.onNoVehicleRegisteredWithUser();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
