package com.itg.jobcardmanagement.intro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itg.jobcardmanagement.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IntroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IntroFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int TYPE_TITLE = 1;
    private static final int TYPE_DESC = 2;
    @BindView(R.id.fragment_main)
    LinearLayout fragmentMain;
    Unbinder unbinder;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    private int position;
    private int bgColor;

    // TODO: Rename and change types of parameters


    public IntroFragment() {
        // Required empty public constructor
    }


    public static IntroFragment newInstance(int position, int color) {
        IntroFragment fragment = new IntroFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        args.putInt(ARG_PARAM2, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_PARAM1);
            bgColor = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentMain.setBackgroundColor(bgColor);
        setTitle();
        setDesc();
        return view;
    }

    private void setDesc() {
        txtDesc.setText(getStringFromPosition(position,TYPE_DESC));
    }

    private void setTitle() {
        txtTitle.setText(getStringFromPosition(position,TYPE_TITLE));
    }

    private String getStringFromPosition(int position,int type){
        switch (position){
            case 0:
               return getString(type==TYPE_TITLE?R.string.slide_1_title:R.string.slide_1_desc);
            case 1:
                return getString(type==TYPE_TITLE?R.string.slide_2_title:R.string.slide_2_desc);
            case 2:
                return getString(type==TYPE_TITLE?R.string.slide_3_title:R.string.slide_3_desc);
        }
        return "";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
