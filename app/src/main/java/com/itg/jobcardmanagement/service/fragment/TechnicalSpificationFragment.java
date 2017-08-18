package com.itg.jobcardmanagement.service.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.service.adapter.TechnicalSpificationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TechnicalSpificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TechnicalSpificationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.listview)
    ListView recyclerViewDescription;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private android.content.Context mContext;


    public TechnicalSpificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TechnicalSpificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TechnicalSpificationFragment newInstance(String param1, String param2) {
        TechnicalSpificationFragment fragment = new TechnicalSpificationFragment();
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
        View view = inflater.inflate(R.layout.fragment_technical_spification, container, false);
        unbinder = ButterKnife.bind(this, view);
        setRecylerView();
        return view;
    }

    private void setRecylerView() {
//        recyclerViewDescription.setLayoutManager(new LinearLayoutManager(mContext));
//        recyclerViewDescription.setAdapter(new TechnicalSpificationAdapter(mContext));
        recyclerViewDescription.setAdapter(new TechnicalSpificationAdapter(mContext));
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
        if(mContext!= null)
        {
            mContext = null;
        }
    }
}