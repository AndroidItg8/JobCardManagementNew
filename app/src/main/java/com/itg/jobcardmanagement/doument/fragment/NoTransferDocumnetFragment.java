package com.itg.jobcardmanagement.doument.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.registration.CustomerRegistrationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoTransferDocumnetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoTransferDocumnetFragment extends DialogFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.lbl_form)
    TextView lblForm;
    @BindView(R.id.edt_title)
    TextInputEditText edtTitle;

    @BindView(R.id.edt_description)
    TextInputEditText edtDescription;

    @BindView(R.id.btn_submit)
    Button btnSubmit;
    Unbinder unbinder;
     private Context mContext;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public NoTransferDocumnetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoTransferDocumnetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoTransferDocumnetFragment newInstance(String param1, String param2) {
        NoTransferDocumnetFragment fragment = new NoTransferDocumnetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setLayout(params.width,params.height);

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
        View view = inflater.inflate(R.layout.fragment_no_transfer_documnet, container, false);
        unbinder = ButterKnife.bind(this, view);

        edtDescription.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_FLAG_MULTI_LINE |
                InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        edtTitle.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        edtTitle.setLines(1);
        edtDescription.setLines(5);
        edtDescription.setMaxLines(5);
        getDialog().setTitle("Query Form");
        btnSubmit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_submit:

                callSubmitButton();
                break;
        }
    }

    private void callSubmitButton() {
        if(validate())
        {
            ((CustomerRegistrationActivity)mContext).sendQueryFormToServer(edtTitle.getText().toString(), edtDescription.getText().toString());
            Toast.makeText(mContext, "Admin will contact you ,After study of your problem... ", Toast.LENGTH_SHORT).show();
            dismiss();

        }
    }

    private boolean validate() {
       boolean isValidate = true;

        if(TextUtils.isEmpty(edtTitle.getText().toString()))
        {
            edtTitle.setError("Field is empty");
            isValidate = false;
        }
         if(TextUtils.isEmpty(edtDescription.getText().toString()))
         {
             edtDescription.setError("Field is empty");
             isValidate = false;
         }
         return  isValidate;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
         this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
         if(mContext != null)
         {
              mContext = null;
         }
    }
}
