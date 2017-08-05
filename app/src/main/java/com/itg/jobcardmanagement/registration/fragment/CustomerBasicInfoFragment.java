package com.itg.jobcardmanagement.registration.fragment;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itg.jobcardmanagement.R;

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
    @BindView(R.id.edt_name)
    TextInputEditText edtName;
    @BindView(R.id.input_name)
    TextInputLayout inputName;
    @BindView(R.id.edt_contact)
    TextInputEditText edtContact;
    @BindView(R.id.input_contact)
    TextInputLayout inputContact;
    @BindView(R.id.edt_address)
    TextInputEditText edtAddress;
    @BindView(R.id.input_address)
    TextInputLayout inputAddress;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CustomerBasicInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerBasicInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerBasicInfoFragment newInstance(String param1, String param2) {
        CustomerBasicInfoFragment fragment = new CustomerBasicInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_customer_basic_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        edtAddress.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_FLAG_MULTI_LINE |
                InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
