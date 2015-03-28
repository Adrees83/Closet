package com.hackust.closet;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.hackust.closet.closet.InstagramCloset;


public class ClosetActivity extends ActionBarActivity {

    // instagramClosetButton

    Activity activ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        activ = this;
        // implementing button actions
        ImageButton insButton = (ImageButton) findViewById(R.id.instagramClosetButton);


        insButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(activ, InstagramCloset.class);
                startActivity(i);
            }
        });

        setUpDataForMockUp();
    }

    public void setUpDataForMockUp(){
        // set up false data for the system
        int data[] = new int[] {R.drawable.ic_launcher, R.drawable.logosmall};
        int leased[] = new  int[] {1,0};

        SharedPreferences settings = getApplicationContext().getSharedPreferences("Closet", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("num", 2);
        for (int i= 0; i <data.length; i++){
            editor.putInt("c"+i+"loc", data[i]);
            editor.putInt("c"+i+"les", leased[i]);
        }
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_closet, menu);
        setTitle("Closet");
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

        return super.onOptionsItemSelected(item);
    }
}
