package com.hackust.closet.closet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.hackust.closet.ClosetActivity;
import com.hackust.closet.ExploreActivity;
import com.hackust.closet.FaqActivity;
import com.hackust.closet.MainActivity;
import com.hackust.closet.ProfileActivity;
import com.hackust.closet.R;

public class ClosetItem extends ActionBarActivity {

    Activity activ;
    int leasedd;
    Button insButton;
    SharedPreferences.Editor editor;
    int itemtoShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet_item);

        activ = this;

        Bundle extras = getIntent().getExtras();

        itemtoShow = extras.getInt("imageid");

        SharedPreferences settings = getApplicationContext().getSharedPreferences("Closet", 0);
        editor = settings.edit();
        int itemcode = settings.getInt("c"+itemtoShow+"loc", 0);
        leasedd = settings.getInt("c"+itemtoShow+"les", 0);

        ImageView iv= (ImageView)findViewById(R.id.imageViewClosetItem);
        iv.setImageResource(itemcode);

        insButton = (Button) findViewById(R.id.closeItemButton);
        if (leasedd == 1){
            insButton.setText("Leasing..");
        }

        insButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (leasedd == 0) {
                    Toast.makeText(activ, "This dress has been put for leased", Toast.LENGTH_LONG).show();
                    insButton.setText("Leasing..");
                    leasedd = 1;
                } else {
                    Toast.makeText(activ, "Not leased anymore", Toast.LENGTH_LONG).show();
                    insButton.setText("Lease");
                    leasedd = 0;
                }
                editor.putInt("c"+itemtoShow+"les", leasedd);
                editor.apply();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_closet_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent = null;
        //noinspection SimplifiableIfStatement
        if(id == R.id.action_stream){
            intent = new Intent(this, MainActivity.class);
        }else if(id == R.id.action_explore){
            intent = new Intent(this, ExploreActivity.class);
        }else if(id == R.id.action_profile){
            intent = new Intent(this, ProfileActivity.class);
        }else if(id == R.id.action_closet){
            intent = new Intent(this, ClosetActivity.class);
        }else if(id == R.id.action_faq){
            intent = new Intent(this, FaqActivity.class);
        }

        startActivity(intent);
        finish();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
