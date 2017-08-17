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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.common.CommonMethod;
import com.itg.jobcardmanagement.common.Prefs;
import com.itg.jobcardmanagement.registration.mvp.LoginRegMVP;
import com.itg.jobcardmanagement.registration.mvp.presenter.PasswordPresenterImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordFragment extends Fragment  implements LoginRegMVP.PasswordView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.in_password)
    TextInputLayout inPassword;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.btn_next)
    Button btnNext;
    Unbinder unbinder;
    RegistrationFragment.OnRegistrationListner listener;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PasswordPresenterImp presenter;


    public PasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PasswordFragment newInstance(String param1, String param2) {
        PasswordFragment fragment = new PasswordFragment();
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
        View view = inflater.inflate(R.layout.fragment_password, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter=new PasswordPresenterImp(this);
        listener= (RegistrationFragment.OnRegistrationListner) context;
    }

    @OnClick(R.id.btn_next)
    public void onClick(){
        presenter.onNextButtonClicked();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onPasswordEmpty() {
        inPassword.setError("Field cannot be empty...");
    }

    @Override
    public void onPasswordInvalid() {
        inPassword.setError("Invalid password. Please provide right one...");
    }

    @Override
    public void onProgressShow() {
        btnNext.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        inPassword.setEnabled(false);
    }

    @Override
    public void onPogressHide() {
        btnNext.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        inPassword.setEnabled(true);
    }

    @Override
    public void onCallFail(String message) {
        Toast.makeText(getActivity(), "Fail: "+message, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getUsername() {
        return Prefs.getString(CommonMethod.USERNAME,null);
    }

    @Override
    public String getPassword() {
        return edtPassword.getText().toString();
    }

    @Override
    public void onSuccessful(Object o) {
        listener.onRegistrationDone();
    }


}
