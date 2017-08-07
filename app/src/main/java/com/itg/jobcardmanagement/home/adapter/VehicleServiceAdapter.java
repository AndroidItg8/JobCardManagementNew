package com.itg.jobcardmanagement.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itg.jobcardmanagement.R;

import butterknife.ButterKnife;

/**
 * Created by Android itg 8 on 8/7/2017.
 */

public class VehicleServiceAdapter extends RecyclerView.Adapter<VehicleServiceAdapter.ServiceViewHolder> {

    private Context mContext;

    public VehicleServiceAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service,parent, false);
        ServiceViewHolder  holder = new ServiceViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        public ServiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
