// Mobile Platform Development Coursework 2018-2019
// Ian Campbell 200507045

package com.mpd.coursework.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkChecker {

    public static boolean networkAvailable (Context context) {

        ConnectivityManager conMan = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            NetworkInfo activeNetwork =conMan.getActiveNetworkInfo();
            return activeNetwork !=null &&
                    activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
