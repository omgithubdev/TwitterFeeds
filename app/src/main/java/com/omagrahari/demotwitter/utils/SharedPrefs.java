package com.omagrahari.demotwitter.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by omprakash on 25,December,2019
 */
public class SharedPrefs {
    private static final String AUTH_TOKEN = "KYC_TOKEN";
    private static final String USER_NAME = "USER_NAME";

    public static String getToken(SharedPreferences sharedPref) {
        return sharedPref.getString(AUTH_TOKEN, "");
    }

    public static void setoken(SharedPreferences sharedPref, String token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(AUTH_TOKEN, token);
        editor.apply();
    }

    public static String getUserName(SharedPreferences sharedPref) {
        return sharedPref.getString(USER_NAME, "");
    }

    public static void setUserName(SharedPreferences sharedPref, String token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_NAME, token);
        editor.apply();
    }

    public static void clearPref(SharedPreferences sharedPref) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }
}
