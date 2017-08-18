package com.itg.jobcardmanagement.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itg.jobcardmanagement.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android itg 8 on 8/7/2017.
 */

public class VehicleServiceAdapter extends RecyclerView.Adapter<VehicleServiceAdapter.ServiceViewHolder> {

    private Context mContext;
    private ServiceItem listener;

    public VehicleServiceAdapter(Context mContext, ServiceItem listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        ServiceViewHolder holder = new ServiceViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public interface ServiceItem {
        void onServiceItemClickedListener(int adapterPosition);
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.img_car)
        ImageView imgCar;
        @BindView(R.id.txt_companyName)
        TextView txtCompanyName;
        @BindView(R.id.txt_registration)
        TextView txtRegistration;
        @BindView(R.id.txt_date)
        TextView txtDate;

        public ServiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onServiceItemClickedListener(getAdapterPosition());
                }
            });
        }
    }
}
