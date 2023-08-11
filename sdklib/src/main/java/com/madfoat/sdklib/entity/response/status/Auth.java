package com.madfoat.sdklib.entity.response.status;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Hany on 5/11/2017.
 */

@Root(strict = false)
public class Auth implements Parcelable {

    @Element
    private String status;
    @Element
    private String code;
    @Element
    private String message;
    @Element
    private String tranref;
//    @Element
//    private String expiry;
@Element(required = false)
private String expiry;

//------------------------------------------
    @Element(required = false)
    private Card card;
//=-------------------------------------------

    @Element(required = false)
    private String cvv;

    @Element(required = false)
    private String avs;

    @Element(required = false)
    private String cardcode;

    @Element(required = false)
    private String cardlast4;

    @Element(required = false)
    private String ca_valid;

    @Element(required = false)
    private String cardfirst6;

    @Element(required = false)
    private String country;

    @Element(required = false)
    private String month;

    @Element(required = false)
    private String year;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTranref() {
        return tranref;
    }

    public void setTranref(String tranref) {
        this.tranref = tranref;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getAvs() {
        return avs;
    }

    public void setAvs(String avs) {
        this.avs = avs;
    }

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }

    public String getCardlast4() {
        return cardlast4;
    }

    public void setCardlast4(String cardlast4) {
        this.cardlast4 = cardlast4;
    }

    public String getCa_valid() {
        return ca_valid;
    }

    public void setCa_valid(String ca_valid) {
        this.ca_valid = ca_valid;
    }

    public String getCardfirst6() {
        return cardfirst6;
    }

    public void setCardfirst6(String cardfirst6) {
        this.cardfirst6 = cardfirst6;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.code);
        dest.writeString(this.message);
        dest.writeString(this.tranref);
        dest.writeString(this.cvv);
        dest.writeString(this.avs);
        dest.writeString(this.cardcode);
        dest.writeString(this.cardlast4);
        dest.writeString(this.ca_valid);
        dest.writeString(this.cardfirst6);
        dest.writeString(this.expiry);
        dest.writeString(this.country);
        dest.writeString(this.year);
        dest.writeString(this.month);
        dest.writeParcelable(this.card,flags);
    }

    public Auth() {
    }

    protected Auth(Parcel in) {
        this.status = in.readString();
        this.code = in.readString();
        this.message = in.readString();
        this.tranref = in.readString();
        this.cvv = in.readString();
        this.avs = in.readString();
        this.cardcode = in.readString();
        this.cardlast4 = in.readString();
        this.ca_valid = in.readString();
        this.cardfirst6 = in.readString();
        this.expiry=in.readString();
        this.country=in.readString();
        this.month=in.readString();
        this.year=in.readString();
        this.card = in.readParcelable(Card.class.getClassLoader());
    }

    public static final Creator<Auth> CREATOR = new Creator<Auth>() {
        @Override
        public Auth createFromParcel(Parcel source) {
            return new Auth(source);
        }

        @Override
        public Auth[] newArray(int size) {
            return new Auth[size];
        }
    };


}
