package com.madfoat.sdklib;

import android.app.Application;
import android.content.Context;
import android.util.Log;


public class MadfoatApplication extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        Log.d("Hany","Context Started....");
        MadfoatApplication.context = getApplicationContext();
    }

    public static Context getContext(){
        return MadfoatApplication.context;
    }
}
