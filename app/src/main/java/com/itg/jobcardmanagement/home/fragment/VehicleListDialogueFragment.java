package com.itg.jobcardmanagement.home.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.home.adapter.VehicleListAdapter;
import com.itg.jobcardmanagement.widget.SimpleDividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VehicleListDialogueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VehicleListDialogueFragment extends DialogFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recyclerViewVehicle)
    RecyclerView recyclerViewVehicle;
    Unbinder unbinder;
    @BindView(R.id.btnOk)
    Button btnOk;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;


    public VehicleListDialogueFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VehicleListDialogueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VehicleListDialogueFragment newInstance(String param1, String param2) {
        VehicleListDialogueFragment fragment = new VehicleListDialogueFragment();
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         getDialog().setTitle("Vehicle List");
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setLayout(params.width, params.height);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vehicle_list_dialogue, container, false);
        unbinder = ButterKnife.bind(this, view);
        setRecyclerView();
        btnOk.setOnClickListener(this);
        return view;
    }

    private void setRecyclerView() {
        recyclerViewVehicle.setLayoutManager(new LinearLayoutManager(mContext));
        SimpleDividerItemDecoration itemDecoration = new SimpleDividerItemDecoration(recyclerViewVehicle.getContext());
        recyclerViewVehicle.addItemDecoration(itemDecoration);
        recyclerViewVehicle.setAdapter(new VehicleListAdapter(mContext));


    }

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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnOk:
                getDialog().dismiss();
                break;
        }
    }
}
