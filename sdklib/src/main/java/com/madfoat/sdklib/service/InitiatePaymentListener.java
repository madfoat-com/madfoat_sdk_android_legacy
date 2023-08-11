package com.madfoat.sdklib.service;

import org.springframework.http.ResponseEntity;

/**
 * Created by Dell on 5/3/2017.
 */

public interface InitiatePaymentListener {

    public void onPaymentLoadPageSuccess(ResponseEntity<?> response);

    public void onPaymentLoadPageFailure(ResponseEntity<?> response);
}
