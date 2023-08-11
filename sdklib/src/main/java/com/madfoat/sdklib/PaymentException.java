package com.madfoat.sdklib;



public class PaymentException extends Exception {

    public int errorCode;

    public PaymentException(){
        super();
    }

    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, int errorCode) {
        super(message + "; Error code :" + errorCode);
        this.errorCode = errorCode;
    }

    public int getErrorCode(){
        return this.errorCode;
    }
}
