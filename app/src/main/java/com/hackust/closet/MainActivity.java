package com.hackust.closet;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hackust.closet.stream.SimpleArrayAdapter;
import com.hackust.closet.suscriptor.SubscribeActivity;
import com.parse.Parse;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    //int[] arr = {R.drawable.im3,R.drawable.im3,R.drawable.im3,R.drawable.im3,R.drawable.im3};
    int[] arr;

    Activity activ;
    int which = 0;  // tell u which part are you watchin, form the orignial main or from the one comes from subscirbe

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activ = this;


        Bundle extra = getIntent().getExtras();
        if (extra == null){
            which = 0;
        }else{
            if (extra.getString("fromsubscribe") != null){
                which = 1;
            } else {
                which = 0;
            }
        }

        Parse.initialize(this, "wjICC0rXV1eBpnfe1BI2b7FPQ09Qp9lgPkUxh8PW", "21DGljAKmXZwHdFXZM9R39o23qGDyPR24sRXoHF2");

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        // check if he is comming from subscribe to show more data
        //Bundle extras = this.getIntent().getExtras();
        //if (extras.getString("fromsubscribe") == null){
        if(which == 0)
            arr = DataSet.images_normal_main;
        else
            arr = DataSet.images_from_subscribe;

        SimpleArrayAdapter adapter = new SimpleArrayAdapter(this, arr);
        ListView lv = (ListView) findViewById(R.id.listView_main);
        lv.setAdapter(adapter);
        lv.setDivider(null);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(activ, ItemDetailActivity.class);
                i.putExtra("itemid", (int)position);
                i.putExtra("which", which);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        setTitle("Stream");
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
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}
