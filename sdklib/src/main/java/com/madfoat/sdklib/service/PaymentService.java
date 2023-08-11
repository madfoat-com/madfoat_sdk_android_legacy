package com.madfoat.sdklib.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;


import com.madfoat.sdklib.PaymentException;
import com.madfoat.sdklib.entity.request.payment.Device;
import com.madfoat.sdklib.entity.request.payment.MobileRequest;

import java.io.File;


public class PaymentService {

    private static final String TAG ="PaymentService" ;

    public void isValidRequest(Context context, MobileRequest mobileRequest, String successActivtyName, String failedActivityName, boolean isSecurityEnabled) throws PaymentException {

        if(!validateRequest(mobileRequest, successActivtyName, failedActivityName)) {
            throw new PaymentException("Missing one mandatory value SUCCESS_ACTIVTY_CLASS_NAME, FAILED_ACTIVTY_CLASS_NAME, main Object", 101);
        }
        if(!isInternetAvailable(context)) {
            throw new PaymentException("No Internet connection available", 102);
        }
        if(isSecurityEnabled && isRooted()) {
            throw new PaymentException("Device Security issue, Mobile is Rooted", 103);
        }
    }

    private boolean validateRequest(MobileRequest mobileRequest, String successActivtyName, String failedActivityName) {
        if(mobileRequest == null || successActivtyName == null || failedActivityName == null){
            return false;
        }else{
            return true;
        }
    }

    public Device getDeviceDetails(Context context){
        Device device = new Device();
        device.setType("Android");
        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        device.setId(deviceId); // "36C0EC49-AA2F-47DC-A4D7-D9927A739F5F"
        return device;
    }

    private boolean isRooted() {

        // get from build info
        String buildTags = android.os.Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }

        // check if /system/app/Superuser.apk is present
        try {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                return true;
            }
        } catch (Exception e1) {
            // ignore
        }

        // try executing commands
        return checkRootMethod();
    }

    private boolean checkRootMethod() {
        String[] paths = { "su/bin/su", "/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su" };
        for (String path : paths) {
            if (new File(path).exists()) return true;
        }
        return false;
    }


    private boolean isInternetAvailable(Context context) {
        ConnectivityManager check = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(check!=null&&check.getAllNetworkInfo()!=null)
        {
            NetworkInfo[] info = check.getAllNetworkInfo();

            for (NetworkInfo local : info)
            {
                if (local.isConnected())
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void updateSDKVersion(MobileRequest mobileRequest) {
        mobileRequest.getTran().setVersion("2");
        mobileRequest.getApp().setSdk("SDK Ver 2.0");
    }
}
