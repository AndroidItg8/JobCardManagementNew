package com.itg.jobcardmanagement.registration;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.registration.mvp.RegMVP;
import com.itg.jobcardmanagement.registration.mvp.RegPresenterImp;

import java.io.CharArrayWriter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment implements View.OnClickListener, RegMVP.RegView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int FNAME = 1;
    @BindView(R.id.edt_fName)
    EditText edtFName;
    @BindView(R.id.in_fName)
    TextInputLayout inFName;
    @BindView(R.id.edt_lName)
    EditText edtLName;
    @BindView(R.id.in_lName)
    TextInputLayout inLName;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.in_password)
    TextInputLayout inPassword;
    @BindView(R.id.edt_cpassword)
    EditText edtCpassword;
    @BindView(R.id.in_cpassword)
    TextInputLayout inCpassword;
    Unbinder unbinder;
    @BindView(R.id.btn_next)
    Button btnNext;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RegMVP.RegPresenter presenter;
    private StringBuilder sb;
    OnRegistrationListner listener;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance(String param1, String param2) {
        RegistrationFragment fragment = new RegistrationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        unbinder = ButterKnife.bind(this, view);
        sb=new StringBuilder();
        btnNext.setOnClickListener(this);
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter=new RegPresenterImp(this);
        listener= (OnRegistrationListner) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onClick(View view) {
        presenter.onNextButtonClicked();
    }

    @Override
    public void onPasswordNotMatch() {
        edtPassword.setError("Invalid");
        edtCpassword.setError("Invalid");
        Toast.makeText(getActivity(), "Password not match", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordConditionFail() {
        edtPassword.setError("Password should be greater than 6 character");
        edtCpassword.setError("Invalid");
        Toast.makeText(getActivity(), "Password condition fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordEmpty() {
        edtPassword.setError("Should not be empty");
        edtPassword.requestFocus();
    }

    @Override
    public void onFirstnameEmpty() {
        edtFName.setError("Should not be empty");
        edtFName.requestFocus();
    }

    @Override
    public void onLastnameEmpty() {
        edtLName.setError("Should not be empty");
        edtLName.requestFocus();
    }

    @Override
    public void onNameContainingInteger(int field) {
        if(field==FNAME){
            edtFName.setError("Name should not contains number");
            edtFName.requestFocus();
        }else {
            edtLName.setError("Name should not contains number");
            edtLName.requestFocus();
        }
    }

    @Override
    public void onRegistrationFail(String message) {
        Toast.makeText(getActivity(), sb.append("Registration fail: ").append(message).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegistrationSuccessful(Object... obj) {
        if(obj.length>0){
            if(listener!=null){
                listener.onRegistrationDone();
            }
        }
    }

    @Override
    public String getPassword() {
        return edtPassword.getText().toString();
    }

    @Override
    public String getFName() {
        return edtFName.getText().toString();
    }

    @Override
    public String getLName() {
        return edtLName.getText().toString();
    }

    @Override
    public String getCPassword() {
        return edtCpassword.getText().toString();
    }

    @Override
    public void onCpasswordEmpty() {
        edtCpassword.setError("Should not be empty");
        edtCpassword.requestFocus();
    }

    public interface OnRegistrationListner{
            void onRegistrationDone();
    }


}
