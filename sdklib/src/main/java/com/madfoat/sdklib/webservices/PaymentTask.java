package com.madfoat.sdklib.webservices;

import android.os.AsyncTask;
import android.util.Log;


import com.madfoat.sdklib.entity.request.payment.MobileRequest;
import com.madfoat.sdklib.entity.response.payment.MobileResponse;
import com.madfoat.sdklib.service.InitiatePaymentListener;
import com.madfoat.sdklib.utils.LogUtils;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


public class PaymentTask extends AsyncTask<Object, Void, ResponseEntity<?>> {

    private static final String TAGG = PaymentTask.class.getName();
    private static final String TAG = "PaymentTask";
    protected List<InitiatePaymentListener> listeners= new ArrayList<>();

    public PaymentTask(InitiatePaymentListener listener){
        listeners.add(listener);
    }


    @Override
    protected ResponseEntity<?> doInBackground(Object[] params) {
        try {
            String url = (String) params[0];
            MobileRequest request = (MobileRequest) params[1];
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            Serializer serializer = new Persister(new Format("<?xml version=\"1.0\" encoding= \"UTF-8\" ?>"));
            restTemplate.getMessageConverters().add(new SimpleXmlHttpMessageConverter(serializer));

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(new MediaType("application", "xml"));
            HttpEntity<?> requestEntity = new HttpEntity<Object>(request, requestHeaders);
            Log.e(TAG, "doInBackground:requestEntity "+requestEntity.toString());
            ResponseEntity<MobileResponse> result = restTemplate.postForEntity(url, requestEntity, MobileResponse.class);
            LogUtils.logError(TAGG,"PaymentTask Url called"+result.toString());
            return result;

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    protected void onPostExecute(ResponseEntity<?> response) {
        MobileResponse mobileResponse = response!=null?(MobileResponse)response.getBody():null;
        LogUtils.logError(TAGG,"PaymentTask onPostExecute "+mobileResponse);
        LogUtils.logError(TAGG,"PaymentTask onPostExecute "+response);
        if(mobileResponse!=null && HttpStatus.OK.equals(response.getStatusCode()) && mobileResponse.getAuth()== null) {
            for (InitiatePaymentListener listener : listeners) {
                listener.onPaymentLoadPageSuccess(response);
            }
        }else{
            for (InitiatePaymentListener listener : listeners) {
                listener.onPaymentLoadPageFailure(response);
            }
        }
    }



}
