package com.hackust.closet;


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

import java.util.List;

/**
 * Created by aje on 1/12/15.
 */
public class SimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] price_arr ;
    private final List<byte[]> item_pic;
    int[] image_link;

    public SimpleArrayAdapter(Context context, int[] item_arr, int[] desc_arr, List<byte[]> img) {
        super(context, R.layout.list_row, new String[item_arr.length]);
        this.context = context;
        this.image_link = item_arr;
        this.price_arr = new String[] {"50", "100", "200","320","200", "220", "210","105","175", "75", "100","120","75", "400", "300","40","100", "105","175", "75", "100","120","105","175", "75", "100","120","105","175", "75", "100","120"};
        this.item_pic = img;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_row, parent, false);
        TextView textView1 = (TextView) rowView.findViewById(R.id.textView_price);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        if(position < price_arr.length) {
            textView1.setText(price_arr[position]);
            imageView.setImageResource(image_link[position]);
            //Log.d(Constants.TAG, "position:" + position + "itemsize:" + item_arr.length +" Pic size"+item_pic.size()+" desc size: "+desc_arr.length);
        /*    if (position<item_pic.size() && item_pic.get(position) != null) {
                int x = item_pic.get(position).length;
                Bitmap bm = BitmapFactory.decodeByteArray(item_pic.get(position), 0, x);
                imageView.setImageBitmap(bm);
            }
        */
        }
        return rowView;
    }

}
