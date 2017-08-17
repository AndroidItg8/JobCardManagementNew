package com.itg.jobcardmanagement.service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itg.jobcardmanagement.R;

/**
 * Created by Android itg 8 on 8/10/2017.
 */

public class TechnicalSpificationAdapter extends BaseAdapter {


    private Context mContext;

    public TechnicalSpificationAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_technical_facts, null);
            holder = new ViewHolder();
//            holder.mSNo = (TextView) convertView.findViewById(R.id.sNo);
//            holder.mProduct = (TextView) convertView.findViewById(R.id.product);
//            holder.mCategory = (TextView) convertView
//                    .findViewById(R.id.category);
//            holder.mPrice = (TextView) convertView.findViewById(R.id.price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        Model item = productList.get(position);
//        holder.mSNo.setText(item.getsNo().toString());
//        holder.mProduct.setText(item.getProduct().toString());
//        holder.mCategory.setText(item.getCategory().toString());
//        holder.mPrice.setText(item.getPrice().toString());

        return convertView;
    }

    private class ViewHolder {
        TextView mSNo;
        TextView mProduct;
        TextView mCategory;
        TextView mPrice;
    }
}
