package com.hackust.closet.suscriptor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.hackust.closet.ClosetActivity;
import com.hackust.closet.ExploreActivity;
import com.hackust.closet.FaqActivity;
import com.hackust.closet.MainActivity;
import com.hackust.closet.ProfileActivity;
import com.hackust.closet.R;
import com.hackust.closet.SignUpActivity;
import com.hackust.closet.stream.SimpleArrayAdapter;


public class SubscribeActivity extends ActionBarActivity {

    int integer_list[] = new int[]{R.drawable.im1, R.drawable.im7,R.drawable.im3, R.drawable.im6};

    Activity activ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        activ = this;
        SuscriptorSimpleAdapter adapter = new SuscriptorSimpleAdapter(this, integer_list);
        ListView lv = (ListView) findViewById(R.id.suscribelistView_main);
        lv.setAdapter(adapter);

        Button signupButton = (Button)findViewById(R.id.exploresearch);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activ, MainActivity.class);
                intent.putExtra("fromsubscribe","yes");
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subscribe, menu);
        setTitle("Subscribe to channel");
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
