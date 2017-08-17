package com.itg.jobcardmanagement.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itg.jobcardmanagement.R;

import java.util.HashMap;


/**
 * Created by Android itg 8 on 8/10/2017.
 */

public class ExteriorListAdapter extends BaseAdapter {
   private HashMap<String,Integer> hashMap = new HashMap<>();
    private static LayoutInflater inflater=null;
    private Context applicationContext;
    private HashMap<String, Integer> list_exterior= new HashMap<>();

    public ExteriorListAdapter(Context applicationContext, HashMap<String, Integer> list_exterior) {

        this.applicationContext = applicationContext;
        this.list_exterior = list_exterior;
    }

    @Override
    public int getCount() {
        return list_exterior.size();
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
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.item_single, null);


        TextView title = (TextView)vi.findViewById(R.id.label); // title

        title.setText(list_exterior.get(list_exterior.keySet()));

//        song = data.get(position);
//
//        // Setting all values in listview
//        title.setText(song.get(CustomizedListView.KEY_TITLE));
//        artist.setText(song.get(CustomizedListView.KEY_ARTIST));
//        duration.setText(song.get(CustomizedListView.KEY_DURATION));
//        imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
        return vi;
    }
}
