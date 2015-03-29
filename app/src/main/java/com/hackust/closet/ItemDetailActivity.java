package com.hackust.closet;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ItemDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Bundle extras = getIntent().getExtras();
        int posi = extras.getInt("itemid");
        int which = extras.getInt("which");
        // get data about the element position
        ImageView imageView = (ImageView) findViewById(R.id.detailImageid);
        if(which == 0){
            imageView.setImageResource(DataSet.images_normal_main[posi]);
        }else{
            imageView.setImageResource(DataSet.images_from_subscribe[posi]);
        }

        TextView v1 = (TextView) findViewById(R.id.itemprice);
        v1.setText("Prices: " + DataSet.prices[posi]);
        TextView v2 = (TextView) findViewById(R.id.itemyear);
        v2.setText("Year: " + DataSet.year[posi]);

        Button btn = (Button) findViewById(R.id.btn_borrow);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ItemDetailActivity.this,Borrow_Activity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
