package com.segwaydiscovery.bledemo.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.segwaydiscovery.bledemo.IoTManagerApplication;

public class PreferencesUtil {

    public static final String PREFERENCES = "IoTManager";

    private static SharedPreferences getSharedPreferences() {
        return IoTManagerApplication.getInstance().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return getSharedPreferences().getBoolean(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        getSharedPreferences().edit().putBoolean(key, value).apply();
    }

    public static String getString(String key, String defValue) {
        return getSharedPreferences().getString(key, defValue);
    }

    public static void putString(String key, String value) {
        getSharedPreferences().edit().putString(key, value).apply();
    }

    public static int getInt(String key) {
        return getSharedPreferences().getInt(key, 0);
    }

    public static void putInt(String key, int value) {
        getSharedPreferences().edit().putInt(key, value).apply();
    }

}
