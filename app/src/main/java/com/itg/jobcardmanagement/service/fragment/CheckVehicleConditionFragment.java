package com.itg.jobcardmanagement.service.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.service.adapter.PhysicalFactsAdapter;
import com.itg.jobcardmanagement.widget.SimpleDividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckVehicleConditionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckVehicleConditionFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rgb_dent)
    RadioButton rgbDent;
    @BindView(R.id.rgb_scratch)
    RadioButton rgbScratch;
    @BindView(R.id.rgb_Peel)
    RadioButton rgbPeel;
    @BindView(R.id.rgb_Cut)
    RadioButton rgbCut;
    @BindView(R.id.rgb_feedback)
    RadioGroup rgbFeedback;
    @BindView(R.id.rl_radio)
    RelativeLayout rlRadio;
    //    @BindView(R.id.frame_physical_facts)
//    FrameLayout framePhysicalFacts;
    Unbinder unbinder;
    @BindView(R.id.recyclerViewPhysicalFacts)
    RecyclerView recyclerViewPhysicalFacts;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private FragmentManager fm;


    public CheckVehicleConditionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckVehicleConditionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckVehicleConditionFragment newInstance(String param1, String param2) {
        CheckVehicleConditionFragment fragment = new CheckVehicleConditionFragment();
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
        View view = inflater.inflate(R.layout.fragment_check_vehicle_condition, container, false);
        unbinder = ButterKnife.bind(this, view);
        setRecyclerView();

        init();


        return view;
    }

    private void init() {
        rgbCut.setOnCheckedChangeListener(this);
        rgbDent.setOnCheckedChangeListener(this);
        rgbPeel.setOnCheckedChangeListener(this);
        rgbScratch.setOnCheckedChangeListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.rgb_Cut:
               // setFragment();
                setRecyclerView();

                break;
            case R.id.rgb_dent:
//                setFragment();
                setRecyclerView();

                break;
            case R.id.rgb_Peel:
//                setFragment();
                setRecyclerView();

                break;
            case R.id.rgb_scratch:
                setRecyclerView();
                break;

        }

    }

    private void setFragment() {
//        fm = getChildFragmentManager();
//        fm.beginTransaction().replace(R.id.frame_physical_facts,PhysicalFactsFragment.newInstance("","")).commit();
    }

    private void setRecyclerView() {
        recyclerViewPhysicalFacts.setLayoutManager(new LinearLayoutManager(mContext));
        SimpleDividerItemDecoration itemDecoration = new SimpleDividerItemDecoration(recyclerViewPhysicalFacts.getContext());
        recyclerViewPhysicalFacts.addItemDecoration(itemDecoration);
        recyclerViewPhysicalFacts.setAdapter(new PhysicalFactsAdapter(mContext));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (mContext != null) {
            mContext = null;
        }
    }
}
