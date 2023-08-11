package com.madfoat.sdklib.webservices;

import android.os.AsyncTask;


import com.madfoat.sdklib.entity.request.status.StatusRequest;
import com.madfoat.sdklib.entity.response.status.StatusResponse;
import com.madfoat.sdklib.service.StatusListener;

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


public class StatusTask extends AsyncTask<Object, Void, ResponseEntity<?>> {

    protected List<StatusListener> listeners= new ArrayList<>();

    public StatusTask(StatusListener listener){
        listeners.add(listener);
    }


    @Override
    protected ResponseEntity<?> doInBackground(Object[] params) {
        try {
            String url = (String) params[0];
            StatusRequest request = (StatusRequest) params[1];
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            Serializer serializer = new Persister(new Format("<?xml version=\"1.0\" encoding= \"UTF-8\" ?>"));
            restTemplate.getMessageConverters().add(new SimpleXmlHttpMessageConverter(serializer));
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(new MediaType("application", "xml"));
            HttpEntity<?> requestEntity = new HttpEntity<Object>(request, requestHeaders);
            String res= String.valueOf(restTemplate.postForEntity(url,requestEntity, StatusResponse.class));
//            Log.e("res00",":"+res);
            ResponseEntity<StatusResponse> result = restTemplate.postForEntity(url, requestEntity, StatusResponse.class);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ResponseEntity<?> response) {
     //   Log.e("RES1111",response.getBody().toString());
        if(response!=null && HttpStatus.OK.equals(response.getStatusCode())) {
            StatusResponse statusResponse = (StatusResponse)response.getBody();
            if("Authorised".equals(statusResponse.getAuth().getMessage())) {
                for (StatusListener listener : listeners) {
                    listener.onStatusSucceed(response);
                }
            }else if("Pending".equals(statusResponse.getAuth().getMessage()) ||
                    "Cancelled".equals(statusResponse.getAuth().getMessage())){
                for (StatusListener listener : listeners) {
                    listener.onStatusFailed(response);
                }
            }else{
                for (StatusListener listener : listeners) {
                    listener.onStatusFailed(response);
                }
            }
        }else{
            for (StatusListener listener : listeners) {
                listener.onStatusFailed(response);
            }
        }
    }

}
