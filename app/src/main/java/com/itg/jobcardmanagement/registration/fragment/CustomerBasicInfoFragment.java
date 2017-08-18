package com.itg.jobcardmanagement.registration.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.registration.CustomerRegistrationActivity;
import com.itg.jobcardmanagement.registration.model.RegistrationModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerBasicInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerBasicInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Unbinder unbinder;
    @BindView(R.id.edt_name)
    TextInputEditText edtName;
    @BindView(R.id.input_name)
    TextInputLayout inputName;
    @BindView(R.id.edt_l_name)
    TextInputEditText edtLName;
    @BindView(R.id.input_l_name)
    TextInputLayout inputLName;
    @BindView(R.id.edt_contact)
    TextInputEditText edtContact;
    @BindView(R.id.input_contact)
    TextInputLayout inputContact;
    @BindView(R.id.edt_address)
    TextInputEditText edtAddress;
    @BindView(R.id.input_address)
    TextInputLayout inputAddress;
    @BindView(R.id.edt_city)
    TextInputEditText edtCity;
    @BindView(R.id.input_city)
    TextInputLayout inputCity;
    @BindView(R.id.edt_state)
    TextInputEditText edtState;
    @BindView(R.id.input_state)
    TextInputLayout inputState;
    @BindView(R.id.input_email)
    TextInputLayout inputEmail;
    private String mParam1;
    private String mParam2;
    private RegistrationModel model;
    private Context mContext;
    private FragmentManager fm;


    public CustomerBasicInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * <p>
     * <<<<<<< HEAD
     *
     * @return A new instance of fragment CustomerBasicInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerBasicInfoFragment newInstance(RegistrationModel model) {
        CustomerBasicInfoFragment fragment = new CustomerBasicInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, model);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            model = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_basic_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        edtAddress.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_FLAG_MULTI_LINE |
                InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        setModelToWidget();
        checkRegistrationModel();


        return view;
    }

    private void checkRegistrationModel() {
        if (model != null) {
            if (validate()) {
                ((CustomerRegistrationActivity) getActivity()).sendBaiscInfoToActivity(edtName.getText().toString(), edtLName.getText().toString(), edtContact.getText().toString(), edtAddress.getText().toString(), edtCity.getText().toString(), edtState.getText().toString());
            } else {
                Toast.makeText(getActivity(), "Field are not match", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean validate() {
        boolean isValidate = true;

        if (TextUtils.isEmpty(edtName.getText().toString())) {
            edtName.setError("Field is empty");
            isValidate = false;
        }
        if (TextUtils.isEmpty(edtLName.getText().toString())) {
            edtLName.setError("Field is empty");
            isValidate = false;
        }
        if (TextUtils.isEmpty(edtContact.getText().toString())) {
            edtContact.setError("Field is empty");
            isValidate = false;
        } else if (edtContact.getText().length() != 10) {
            edtContact.setError("Enter Valid Number");
            isValidate = false;
        }
        if (TextUtils.isEmpty(edtAddress.getText().toString())) {
            edtAddress.setError("Field is empty");
            isValidate = false;
        }
        if (TextUtils.isEmpty(edtCity.getText().toString())) {
            edtCity.setError("Field is empty");
            isValidate = false;
        }
        if (TextUtils.isEmpty(edtState.getText().toString())) {
            edtState.setError("Field is empty");
            isValidate = false;

//            if (model != null) {
//                if (validate()) {
//                    ((CustomerRegistrationActivity) getActivity()).sendBaiscInfoToActivity(edtName.getText().toString(), edtLName.getText().toString(), edtContact.getText().toString(), edtAddress.getText().toString(), edtCity.getText().toString(), edtState.getText().toString());
//                } else {
//                    Toast.makeText(getActivity(), "Field are empty ", Toast.LENGTH_SHORT).show();
//                }
//            }

        }
        return isValidate;
    }

    private void setModelToWidget() {
        if (model != null) {

            edtName.setText(model.getFirstName());
            edtLName.setText(model.getLastName());
            edtAddress.setText(model.getCustomerAddress());
            edtState.setText(model.getCustomerState());
            edtCity.setText(model.getCustomerCity());
            edtContact.setText(model.getContactNumber());

            checkPossibilites();
        }


    }

    private void checkPossibilites() {
//        if (!model.getContactNumber().equalsIgnoreCase(edtContact.getText().toString())
//                && !model.getCustomerEmail().equalsIgnoreCase(edtEmail.getText().toString())) {
//            if (TextUtils.isEmpty(model.getCustomerTransfertFilePath())) {
//                openQueryForm();
//            }
//        }
    }


//    private boolean validate() {
//        boolean isValidate = true;
//
//        if (TextUtils.isEmpty(edtName.getText().toString())) {
//            edtName.setError("Field is empty");
//            isValidate = false;
//        }
//        if (TextUtils.isEmpty(edtLName.getText().toString())) {
//            edtLName.setError("Field is empty");
//            isValidate = false;
//        }
//        if (TextUtils.isEmpty(edtContact.getText().toString())) {
//            edtContact.setError("Field is empty");
//            isValidate = false;
//        } else if (edtContact.getText().length() != 10) {
//            edtContact.setError("Enter Valid Number");
//            isValidate = false;
//        }
//        if (TextUtils.isEmpty(edtAddress.getText().toString())) {
//            edtAddress.setError("Field is empty");
//            isValidate = false;
//        }
//        if (TextUtils.isEmpty(edtCity.getText().toString())) {
//            edtCity.setError("Field is empty");
//            isValidate = false;
//        }
//        if (TextUtils.isEmpty(edtState.getText().toString())) {
//            edtState.setError("Field is empty");
//            isValidate = false;
//        }
//        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
//            edtEmail.setError("Field is empty");
//            isValidate = false;
//        }
//
//        return isValidate;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mContext != null) {
            mContext = null;
        }
    }
}
