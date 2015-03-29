package com.hackust.closet.stream;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackust.closet.DataSet;
import com.hackust.closet.R;

import java.util.List;

/**
 * Created by aje on 1/12/15.
 */
public class SimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    int[] image_link;

    public SimpleArrayAdapter(Context context, int[] item_arr) {
        super(context, R.layout.list_row, new String[item_arr.length]);
        this.context = context;
        this.image_link = item_arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_row, parent, false);
        TextView textView1 = (TextView) rowView.findViewById(R.id.textView_price);
        //TextView textView2 = (TextView) rowView.findViewById(R.id.textview_year);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        textView1.setText("Price: " + DataSet.prices[position]);
       // textView2.setText("Year: " + DataSet.year[position]);
        imageView.setImageResource(image_link[position]);

        return rowView;
    }

}
