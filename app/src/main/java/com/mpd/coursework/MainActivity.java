package com.mpd.coursework;

//
// Name                 _Ian Campbell__
// Student ID           _200507045_____
// Programme of Study   _Information Technology Management for Business

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;

import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.mpd.coursework.model.WidgetClass;
import com.mpd.coursework.service.MyServices;
import com.mpd.coursework.utils.NetworkChecker;

import java.util.List;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    private TextView output;
    private Button startButton;
    private static final String urlSource =
            "http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";
    private boolean haveNetworkAccess;

    private BroadcastReceiver aBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           // String message = intent.getStringExtra(MyServices.MY_SERVICE_LOAD);
            WidgetClass[] widgetClass = (WidgetClass[]) intent.getParcelableArrayExtra
                    (MyServices.MY_SERVICE_LOAD);
            for (WidgetClass item: widgetClass)
                  {
                      output.append(item.getTitle() + "\n");

            }

         }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up the raw links to the graphical components
        output = findViewById(R.id.output);
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(this);

        haveNetworkAccess = NetworkChecker.networkAvailable(this);
        Log.e("My Tag", "network ok");


        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(
                aBroadcastReceiver, new IntentFilter(MyServices.MY_SERVICE_MESSAGE));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(
                aBroadcastReceiver);
    }


    public void onClick(View view) {
        if (haveNetworkAccess) {
            Intent intent = new Intent(this, MyServices.class);
            intent.setData(Uri.parse(urlSource));
            startService(intent);
        }else {
            Toast.makeText(this, "Sorry the network is not available",
                    Toast.LENGTH_SHORT).show();
        }

        }
    }

