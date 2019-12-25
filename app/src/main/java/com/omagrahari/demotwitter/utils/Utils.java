package com.omagrahari.demotwitter.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by omprakash on 25,December,2019
 */
public class Utils {
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
