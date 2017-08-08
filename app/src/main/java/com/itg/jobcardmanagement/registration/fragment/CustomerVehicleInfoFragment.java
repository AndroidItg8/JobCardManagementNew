package com.itg.jobcardmanagement.registration.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
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
 * Use the {@link CustomerVehicleInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerVehicleInfoFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.edt_series)
    TextInputEditText edtSeries;

    @BindView(R.id.edt_model_code)
    TextInputEditText edtModelCode;

    @BindView(R.id.edt_vin_no)
    TextInputEditText edtVinNo;

    @BindView(R.id.edt_color_code)
    TextInputEditText edtColorCode;

    @BindView(R.id.edt_sale_date)
    TextInputEditText edtSaleDate;

    @BindView(R.id.edt_selling_dealer)
    TextInputEditText edtSellingDealer;

    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private RegistrationModel model;


    public CustomerVehicleInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CustomerVehicleInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerVehicleInfoFragment newInstance(RegistrationModel model) {
        CustomerVehicleInfoFragment fragment = new CustomerVehicleInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, model);
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_customer_vehicle_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        edtSaleDate.setOnClickListener(this);
        prepareData();
        setModelToWidget();
        return view;
    }

    private void setModelToWidget() {
        if(model != null)
        {
           edtSeries.setText(model.getCarSeries());
           edtColorCode.setText(model.getCarColorCode());
           edtModelCode.setText(model.getCarModelCode());
           edtVinNo.setText(model.getCarVinNumber());
           edtSellingDealer.setText(model.getCarSaleDate());
           edtSellingDealer.setText(model.getCarDelearName());
        }
    }

    private void prepareData() {
        if(validate())
        {
            ((CustomerRegistrationActivity)getActivity()).sendVehicleDataToActivity(edtSeries.getText().toString(), edtModelCode.getText().toString(), edtColorCode.getText().toString(),edtSaleDate.getText().toString(),edtVinNo.getText().toString(),edtSellingDealer.getText().toString());
        }else
        {
            Toast.makeText(getActivity(), "Field are not match", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private boolean validate() {
        boolean isValidate = true;

        if(TextUtils.isEmpty(edtSeries.getText().toString())) {
            edtSaleDate.setError("Field is empty");
            isValidate = false;
        } if(TextUtils.isEmpty(edtModelCode.getText().toString())) {
            edtModelCode.setError("Field is empty");
            isValidate = false;
        }
        if(TextUtils.isEmpty(edtColorCode.getText().toString())) {
            edtColorCode.setError("Field is empty");
            isValidate = false;
        }

        if(TextUtils.isEmpty(edtSaleDate.getText().toString())) {
            edtSaleDate.setError("Field is empty");
            isValidate = false;
        }if(TextUtils.isEmpty(edtSellingDealer.getText().toString())) {
            edtSellingDealer.setError("Field is empty");
            isValidate = false;
        }if(TextUtils.isEmpty(edtVinNo.getText().toString())) {
            edtVinNo.setError("Field is empty");
            isValidate = false;
        }

        return isValidate;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.edt_sale_date:
                OpenDateDailogue();
        }
    }

    private void OpenDateDailogue() {

    }
}
