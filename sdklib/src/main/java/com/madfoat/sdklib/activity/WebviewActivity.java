package com.madfoat.sdklib.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.madfoat.sdklib.R;
import com.madfoat.sdklib.entity.request.payment.MobileRequest;
import com.madfoat.sdklib.entity.request.status.StatusRequest;
import com.madfoat.sdklib.entity.response.payment.MobileResponse;
import com.madfoat.sdklib.entity.response.status.StatusResponse;
import com.madfoat.sdklib.service.InitiatePaymentListener;
import com.madfoat.sdklib.service.MadfoatSharedPreference;
import com.madfoat.sdklib.service.PaymentService;
import com.madfoat.sdklib.service.StatusListener;
import com.madfoat.sdklib.utils.LogUtils;
import com.madfoat.sdklib.webservices.PaymentTask;
import com.madfoat.sdklib.webservices.StatusTask;

import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;

@Keep
public class WebviewActivity extends AppCompatActivity implements InitiatePaymentListener, StatusListener {
    SharedPreferences sharedpreferences;
    public static final String EXTRA_MESSAGE = "com.telr.mobile.sdk.MESSAGE";
//    private static final String url = "https://uat-secure.telrdev.com/gateway/mobile.xml";
//    private static final String completeUrl = "https://uat-secure.telrdev.com/gateway/mobile_complete.xml";
    private static final String url = "https://secure.telr.com/gateway/mobile.xml";
    private static final String completeUrl = "https://secure.telr.com/gateway/mobile_complete.xml";
    public static final String SUCCESS_ACTIVTY_CLASS_NAME = "successClass";
    public static final String FAILED_ACTIVTY_CLASS_NAME = "failedClass";
    public static final String IS_SECURITY_ENABLED = "securityEnabled";
    public static final String PAYMENT_RESPONSE = "paymentResponse";

    public static final String MOBILE_REQUEST = "mobileRequest";
    public static final String SUCCESS_ACTIVTY_NAME = "successActivtyName";
    public static final String FAILED_ACTIVITY_NAME = "failedActivityName";
    public static final String IS_SECURITY_ENABLED1 = "isSecurityEnabled";
    private static final String TAGG = WebviewActivity.class.getName();

    private String successActivtyName;
    private String failedActivityName;
    private boolean isSecurityEnabled;
    private MobileRequest mobileRequest;
    private PaymentService paymentService;
    private MadfoatSharedPreference madfoatSharedPreference;
    private PaymentTask paymentTask;
    private WebView webView=null;
    private StatusTask statusTask;
    private boolean isErrorOcuur;
    ProgressBar progressBar;
    String url1;
    private  ValueCallback<Uri[]> uploadMessageCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
      //  Log.e(TAGG,"onCreate");

        try {
            webView =findViewById(R.id.webview);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAllowContentAccess(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setSupportMultipleWindows(true);
            progressBar = findViewById(R.id.progressBar);
            paymentService = new PaymentService();
            madfoatSharedPreference=new MadfoatSharedPreference(WebviewActivity.this);
            Intent intent = getIntent();
            mobileRequest = (MobileRequest) intent.getParcelableExtra(EXTRA_MESSAGE);
            successActivtyName = intent.getStringExtra(SUCCESS_ACTIVTY_CLASS_NAME);
            failedActivityName = intent.getStringExtra(FAILED_ACTIVTY_CLASS_NAME);
            isSecurityEnabled = intent.getBooleanExtra(IS_SECURITY_ENABLED, true);
            paymentService.isValidRequest(this,mobileRequest, successActivtyName, failedActivityName, isSecurityEnabled);
            mobileRequest.setDevice(paymentService.getDeviceDetails(WebviewActivity.this));
            paymentService.updateSDKVersion(mobileRequest);

        } catch (final Exception e) {
            // isErrorOcuur=true;
            showAlertDialog(e.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        return;
////        LogUtils.logError(TAGG,"onBackPressed");
////        Log.e("onBackPressed:",mobileRequest.getTran().getClazz());
//        if(mobileRequest.getTran().getClazz().equalsIgnoreCase("cont")){
//            webView.stopLoading();
//            webView.destroy();
//            statusTask.cancel(true);
//            webView.clearHistory();
//            webView.destroy();
//
//           // this.finish();
//            Log.e("onBackPressed:","Do nothing");
//           // finish();
//        }
//        else
//        {
//            if (webView != null)
//            {
//                webView.clearHistory();
//                webView.destroy();
//                webView=null;
//                closeRunningTasks();
//                super.onBackPressed();
//            }
//        }
//        super.onBackPressed();

    }

    @Override
    public void onResume() {
        LogUtils.logError(TAGG,"onResume");
        if (paymentTask == null) {
            LogUtils.logError(TAGG,"onResume paymentTask null");
            paymentTask = new PaymentTask(this);
            if (mobileRequest != null) paymentTask.execute(url, mobileRequest);
        }
        else {
            Log.e(TAGG,"onResume notifyAllDiv");
          //  paymentTask.notifyAll();
        }

        super.onResume();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        LogUtils.logError(TAGG,"onSaveInstanceState");
        savedInstanceState.putParcelable(MOBILE_REQUEST, mobileRequest);
        savedInstanceState.putString(SUCCESS_ACTIVTY_NAME, successActivtyName);
        savedInstanceState.putString(FAILED_ACTIVITY_NAME, failedActivityName);
        savedInstanceState.putBoolean(IS_SECURITY_ENABLED1, isSecurityEnabled);
        closeRunningTasks();
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.logError(TAGG,"onRestoreInstanceState");
        mobileRequest = savedInstanceState.getParcelable(MOBILE_REQUEST);
        successActivtyName = savedInstanceState.getString(SUCCESS_ACTIVTY_NAME);
        failedActivityName = savedInstanceState.getString(FAILED_ACTIVITY_NAME);
        isSecurityEnabled = savedInstanceState.getBoolean(IS_SECURITY_ENABLED1);
    }


    private void showAlertDialog(final String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.dismiss();
                        try {
                            Intent intent = new Intent(WebviewActivity.this, Class.forName(failedActivityName));
                            intent.putExtra(PAYMENT_RESPONSE, message);
                            startActivity(intent);
                            finish();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        alertDialog.show();
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onPaymentLoadPageSuccess(final ResponseEntity<?> response) {
        try
        {
            if(webView!=null)
            {
                if (webView.getSettings() != null) {
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setAllowContentAccess(true);
                    webView.getSettings().setAllowFileAccess(true);
                    webView.getSettings().setDomStorageEnabled(true);
                    webView.getSettings().setSupportMultipleWindows(true);
                }
                else
                {
                    showAlertDialog("Web View Not Initialized");
                    return;
                }
                webView.setWebViewClient(new WebViewClient()
                {

                    public void onPageStarted(WebView view, String url, Bitmap favicon)
                    {
                        super.onPageStarted(view, url, favicon);
                        LogUtils.logError(TAGG, "onPageStarted " + url);
                        if (url.equals("https://secure.telr.com/gateway/webview_close.html")
                                || url.equals("https://secure.telr.com/gateway/webview_abort.html"))
                        {
                            StatusRequest statusRequest = new StatusRequest();
                            statusRequest.setKey(mobileRequest.getKey());
                            statusRequest.setStore(mobileRequest.getStore());
                            statusRequest.setComplete(((MobileResponse) response.getBody()).getWebview().getCode());
                            statusTask = new StatusTask(WebviewActivity.this);
                            statusTask.execute(completeUrl, statusRequest);
                            madfoatSharedPreference.saveDataToPreference("Code", ((MobileResponse) response.getBody()).getWebview().getCode());
                            progressBar.setVisibility(View.VISIBLE);
                            try{
//
                                String filePath = WebviewActivity.this.getExternalFilesDir("Log").getAbsolutePath()  + "/timestamplog.txt";
                                Runtime.getRuntime().exec(new String[]{"logcat", "-v", "time", "-f", filePath});
                            }catch (NullPointerException | IOException ne){
                                ne.printStackTrace();
                                Log.e("ERROR ON PAYMENT",ne.toString());
                            }

                            // Log.e("CODEXXXX",":"+((MobileResponse) response.getBody()).getWebview().getCode());
                        }

                    }
                });

                //--------------------------------------------------------------------------------------
                webView.setWebChromeClient(new WebChromeClient() {

                    //For Android5.0+
                    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                        uploadMessageCallback = filePathCallback;
                        showChooserDialog();
                        return true;
                    }
                });
//                view.loadUrl(url);
                //-------------------------------------------------------------------------------------
                webView.loadUrl(((MobileResponse) response.getBody()).getWebview().getStart());
                progressBar.setVisibility(View.GONE);
            }
            else
            {
                showAlertDialog("WebView Not Initialized");
            }
        }
        catch (Exception e)
        {

        }

    }

    private void showChooserDialog() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), 1);

    }


    @Override
    public void onPaymentLoadPageFailure(ResponseEntity<?> response) {
        try {
            System.out.println("error:" + response.getStatusCode() + response.getBody());
            MobileResponse resonse = (MobileResponse) response.getBody();
            if(response.getStatusCode().value()==200)
            {
                onStatusSucceedAuth(response);
//                Intent intent = new Intent(WebviewActivity.this, Class.forName(successActivtyName));
////                intent.putExtra(PAYMENT_RESPONSE, (StatusResponse) response.getBody());
////                intent.putExtra("Code", telrSharedPreference.getDataFromPreference("Code"));
////                startActivity(intent);
////                finish();
            }
            else{
                showAlertDialog(resonse.getAuth() != null ? resonse.getAuth().getMessage() : "Error!");
            }

            Log.e("2:","");
        } catch (NullPointerException ne) {
            ne.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onStatusSucceedAuth(ResponseEntity<?> response) {
        progressBar.setVisibility(View.GONE);
        try {
            Intent intent = new Intent();
            intent.putExtra(PAYMENT_RESPONSE, (MobileResponse) response.getBody());
            intent.putExtra("Code", madfoatSharedPreference.getDataFromPreference("Code"));
            intent.putExtra("auth","yes");
            setResult(RESULT_OK,intent);
            finish();
        } catch (NullPointerException | ClassCastException ne) {
            ne.printStackTrace();
        }
    }


    @Override
    public void onStatusSucceed(ResponseEntity<?> response) {
//        try {
        progressBar.setVisibility(View.GONE);
            Intent intent = new Intent();
            intent.putExtra(PAYMENT_RESPONSE,(StatusResponse) response.getBody());
           intent.putExtra("Code",madfoatSharedPreference.getDataFromPreference("Code"));
             intent.putExtra("auth","no");
          //  intent.putExtra("Code","Test"); //telrSharedPreference.getDataFromPreference("Code")
            setResult(RESULT_OK,intent);
            finish();

//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        catch (NullPointerException ne){
//            ne.printStackTrace();
//        }
    }

    @Override
    public void onStatusFailed(ResponseEntity<?> response) {
        try {
            progressBar.setVisibility(View.GONE);
            Intent intent = new Intent();
            intent.putExtra(PAYMENT_RESPONSE,(StatusResponse) response.getBody());
            intent.putExtra("Code",madfoatSharedPreference.getDataFromPreference("Code"));
            intent.putExtra("auth","no");
            setResult(Activity.RESULT_OK,intent);

//            startActivity(intent);
            finish();


//            Intent intent = new Intent(WebviewActivity.this, Class.forName(failedActivityName));
//            intent.putExtra(PAYMENT_RESPONSE, (StatusResponse) response.getBody());
//            intent.putExtra("Code",telrSharedPreference.getDataFromPreference("Code"));
//            startActivity(intent);
//            finish();
        } catch (NullPointerException ne){
            ne.printStackTrace();
        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onStatusPending(ResponseEntity<?> response) {
        try {
            Intent intent = new Intent();
            intent.putExtra(PAYMENT_RESPONSE,(StatusResponse) response.getBody());
            setResult(Activity.RESULT_OK,intent);
//            startActivity(intent);
            finish();

//            Intent intent = new Intent(WebviewActivity.this, Class.forName(failedActivityName));
//            intent.putExtra(PAYMENT_RESPONSE, (StatusResponse) response.getBody());
//            startActivity(intent);
//            finish();

        } catch (NullPointerException ne){
            ne.printStackTrace();
        }
//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onDestroy() {
        webView.destroy();
        super.onDestroy();
        LogUtils.logError(TAGG,"onDestroy");
        if (webView != null)
        {
            webView.clearHistory();
            webView.destroy();
            webView=null;
        }
        closeRunningTasks();

    }

    @Override
    protected void onPause() {
        super.onPause();
//        statusTask.cancel(true);
//        webView.destroy();
        LogUtils.logError(TAGG,"onPause");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ValueCallback<Uri[]> callback = uploadMessageCallback;
        if (requestCode != 1 || callback == null) {return;}
        ArrayList<Uri> results = new ArrayList<Uri>();
        if (resultCode == RESULT_OK) {
            if (data != null) {
                String dataString = data.getDataString();
                ClipData clipData = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    clipData = data.getClipData();
                }
                if (clipData != null) {
                    results.clear();
                    for (int i = 0; i< clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results.set(i, item.getUri());
                    }
                }

                if (dataString != null) {
                    results.clear();
                    results.add(Uri.parse(dataString));
                }
            }
        }
        callback.onReceiveValue(results.toArray(new Uri[0]));
        uploadMessageCallback = null;

    }
    private void closeRunningTasks() {
        LogUtils.logError(TAGG,"closeRunningTasks");
        if (paymentTask != null) {
            Log.e("Inside closerunning","PP");
            paymentTask.cancel(true);

        }

        if (statusTask != null) {
            Log.e("Inside closerunning","SS");
            statusTask.cancel(true);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if(mobileRequest.getTran().getClazz().equalsIgnoreCase("cont")){
            if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                    && keyCode == KeyEvent.KEYCODE_BACK
                    && event.getRepeatCount() == 0) {
                Log.e("CDA", "onKeyDown Called");
                onBackPressed();
                return true;
            }
        }
    else{
    super.onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
