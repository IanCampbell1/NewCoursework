package com.mpd.coursework.service;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.mpd.coursework.Parser.AnXMLParser;
import com.mpd.coursework.model.WidgetClass;
import com.mpd.coursework.utils.Http;

import java.io.IOException;

public class MyServices extends IntentService {

    public static final String TAG = "MyServices";
    public static final String MY_SERVICE_MESSAGE = "myServicesMessage";
    public static final String MY_SERVICE_LOAD = "myServicesLoad";

    public MyServices() {
        super("MyServices");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Uri uri = intent.getData();
        Log.d(TAG, "onHandleIntent:" + uri.toString());

        String response;
        try {
           response = Http.downloadUrl(uri.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        WidgetClass[] widgetClass = AnXMLParser.parseFeed(response);

        Intent messageIntent = new Intent(MY_SERVICE_MESSAGE);
        messageIntent.putExtra(MY_SERVICE_LOAD, widgetClass );
        LocalBroadcastManager manager =
                LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(messageIntent);


    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "onCreate");
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}


