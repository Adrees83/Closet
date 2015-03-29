package com.hackust.closet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class ProfileActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences settings1 = getApplicationContext().getSharedPreferences("Personal", 0);
        SharedPreferences.Editor editor1 = settings1.edit();
        String username = settings1.getString("username","user");
        TextView v1 = (TextView) findViewById(R.id.textViewUsername);
        v1.setText(username);

        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        SharedPreferences settings = getApplicationContext().getSharedPreferences("Closet", 0);
        SharedPreferences.Editor editor = settings.edit();
        int itemcode = settings.getInt("c0loc", 1100);
        if (itemcode == 1100){
            // nothing yet
            imageView.setImageResource(R.drawable.logosmall);
        } else {
            imageView.setImageResource(DataSet.insta[0]);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        setTitle("Profile");
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
