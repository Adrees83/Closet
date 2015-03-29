package com.hackust.closet.suscriptor;

import android.content.Context;
import android.widget.ArrayAdapter;

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

import com.hackust.closet.R;

import java.util.List;

/**
 * Created by aje on 3/29/15.
 */
public class SuscriptorSimpleAdapter extends ArrayAdapter<String> {

    int[] values;
    Context context;

    public SuscriptorSimpleAdapter(Context context, int[] values){
        super(context, R.layout.activity_suscriptor_item, new String[values.length]);
        this.values = values;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        // values from activity_suscriptor_item.xml
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_suscriptor_item, parent, false);

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), values[position]); //values[position]

        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageviewsuscriptor);
        imageView.setImageBitmap(icon);

        return rowView;
    }

}
