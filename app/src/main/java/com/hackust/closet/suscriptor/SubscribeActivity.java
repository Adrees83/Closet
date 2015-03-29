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

import com.hackust.closet.MainActivity;
import com.hackust.closet.R;
import com.hackust.closet.SignUpActivity;
import com.hackust.closet.stream.SimpleArrayAdapter;


public class SubscribeActivity extends ActionBarActivity {

    int integer_list[] = new int[]{R.drawable.im1, R.drawable.im2,R.drawable.im1, R.drawable.im2};

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
