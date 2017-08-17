package com.itg.jobcardmanagement.service.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itg.jobcardmanagement.R;

/**
 * Created by Android itg 8 on 8/9/2017.
 */

public class PhysicalFactsAdapter extends RecyclerView.Adapter<PhysicalFactsAdapter.ViewHolder> {


    private Context mContext;

    public PhysicalFactsAdapter(Context mContext) {

        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_physical_facts,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
