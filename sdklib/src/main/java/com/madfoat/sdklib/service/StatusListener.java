package com.madfoat.sdklib.service;

import org.springframework.http.ResponseEntity;

/**
 * Created by Dell on 5/3/2017.
 */

public interface StatusListener {

    public void onStatusSucceed(ResponseEntity<?> response);

    public void onStatusFailed(ResponseEntity<?> response);

    public void onStatusPending(ResponseEntity<?> response);
}
