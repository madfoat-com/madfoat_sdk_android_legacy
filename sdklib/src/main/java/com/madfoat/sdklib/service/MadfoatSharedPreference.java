package com.madfoat.sdklib.service;

import android.content.Context;
import android.content.SharedPreferences;

public class MadfoatSharedPreference {
    private static final String TAG = "TelrSharedPreference";


    private static MadfoatSharedPreference madfoatSharedPreference;

    private SharedPreferences sharedPreferences;

    public static MadfoatSharedPreference getInstance(Context context) {
        if (madfoatSharedPreference == null) {
            madfoatSharedPreference = new MadfoatSharedPreference(context);
        }
        return madfoatSharedPreference;
    }


    public MadfoatSharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(TAG,Context.MODE_PRIVATE);
    }


    public void saveDataToPreference(String key,String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
//            prefsEditor.clear();
        prefsEditor .putString(key, value);
        prefsEditor.commit();
    }

    public String getDataFromPreference(String key) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }

}
