package com.madfoat.madfoatsampleapp;

import android.content.Context;
import android.util.Log;

import com.madfoat.sdklib.MadfoatApplication;


/**
 * Created by Divya on 10/30/20.
 */

public class DemoApplication extends MadfoatApplication {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        Log.d("Demo","Context Started....");
        DemoApplication.context = getApplicationContext();
    }

    public static Context getContext(){
        return DemoApplication.context;
    }
}
