package com.itg.jobcardmanagement.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itg.jobcardmanagement.R;

import butterknife.ButterKnife;

/**
 * Created by Android itg 8 on 8/10/2017.
 */

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.VehicleViewHolder> {
    private Context mContext;

    public VehicleListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle_list, parent, false);
        VehicleViewHolder holder = new VehicleViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class VehicleViewHolder extends RecyclerView.ViewHolder {
        public VehicleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
