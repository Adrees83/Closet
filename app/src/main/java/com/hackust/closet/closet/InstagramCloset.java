package com.hackust.closet.closet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.hackust.closet.MainActivity;
import com.hackust.closet.R;
import com.hackust.closet.closet.apiCloset.ApplicationData;
import com.hackust.closet.closet.apiCloset.GridAdapter;
import com.hackust.closet.closet.apiCloset.ImageItem;
import com.hackust.closet.closet.apiCloset.InstagramApp;

import java.util.ArrayList;

public class InstagramCloset extends ActionBarActivity {

    private InstagramApp mApp;
    private Button btnConnect;
    private TextView tvSummary;

    Activity activ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_closet);

        activ = this;
        mApp = new InstagramApp(this, ApplicationData.CLIENT_ID,
                ApplicationData.CLIENT_SECRET, ApplicationData.CALLBACK_URL);
        mApp.setListener(listener);

        tvSummary = (TextView) findViewById(R.id.tvSummary);

        btnConnect = (Button) findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mApp.hasAccessToken()) {
                    // already connected
                    final AlertDialog.Builder builder = new AlertDialog.Builder(
                            InstagramCloset.this);
                    builder.setMessage("Disconnect from Instagram?")
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            mApp.resetAccessToken();
                                            btnConnect.setText("Connect");
                                            tvSummary.setText("Not connected");
                                        }
                                    })
                            .setNegativeButton("No",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    final AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    mApp.authorize();   // start autorizathion
                }
            }
        });

        if (mApp.hasAccessToken()) {
            // you are already registred, do the same as later!
            tvSummary.setText("Connected as " + mApp.getUserName());
            btnConnect.setText("Disconnect");
            //
            showCloset();
        }

    }

    private GridView gridView;
    private GridAdapter gridAdapter;

    //int data[] = new int[] {R.drawable.ic_launcher, R.drawable.logosmall};
    int data[];

    public void showCloset(){

        SharedPreferences settings = getApplicationContext().getSharedPreferences("Closet", 0);
        SharedPreferences.Editor editor = settings.edit();
        int numTot = settings.getInt("num", 0);
        // number of elements
        data = new int[numTot];
        for(int i=0; i<numTot; i++){
            data[i] = settings.getInt("c"+i+"loc",0);
        }

        // first remove the icon and the text
        btnConnect.setVisibility(View.GONE);
        tvSummary.setVisibility(View.GONE);
        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridAdapter(this, R.layout.closet_grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                //Toast.makeText(getApplicationContext(),"Item Clicked: " + position, Toast.LENGTH_SHORT).show();
                int selecElement = data[position];

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("imageid", position);

                Intent i = new Intent(activ, ClosetItem.class);
                i.putExtras(dataBundle);
                startActivity(i);

            }
        });
    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        for (int i=0; i< data.length; i++){
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), data[i]);
            imageItems.add(new ImageItem(largeIcon,""));
        }
        return imageItems;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instagram_closet, menu);
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

    InstagramApp.OAuthAuthenticationListener listener = new InstagramApp.OAuthAuthenticationListener() {

        @Override
        public void onSuccess() {
            //tvSummary.setText("Connected as " + mApp.getUserName());
            btnConnect.setText("Disconnect");

            // is correctly connected
            // hide the connect button to show the grid
            //String accessToken = mApp.getAccessToken();
            showCloset();
        }

        @Override
        public void onFail(String error) {
            //Toast.makeText(InstagramCloset.this, error, Toast.LENGTH_SHORT).show();
            btnConnect.setText("Disconnect");
            showCloset();
        }
    };

}
