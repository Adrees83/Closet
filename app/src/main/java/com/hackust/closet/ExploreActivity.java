package com.hackust.closet;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.hackust.closet.suscriptor.SubscribeActivity;


public class ExploreActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        final Activity act = this;
        ImageButton ib = (ImageButton) findViewById(R.id.imageButton_explore);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(act, SubscribeActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_explore, menu);

        setTitle("Explore");
        getSupportActionBar().setLogo(R.drawable.whitelogo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
        else if(id == R.id.home){
        /*    Intent inte = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(inte);
            return true;
        */
        }

        startActivity(intent);
       // finish();

        return super.onOptionsItemSelected(item);
    }
}
