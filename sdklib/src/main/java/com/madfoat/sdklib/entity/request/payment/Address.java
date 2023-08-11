package com.madfoat.sdklib.entity.request.payment;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by hany on 30/04/17.
 */

@Root(strict = false)
public class Address implements Parcelable {

    @Element
    private String line1;
    @Element(required = false)
    private String line2;
    @Element(required = false)
    private String line3;
    @Element(required = false)
    private String zip;
    @Element
    private String city;
    @Element
    private String region;
    @Element
    private String country;

    public String getLine1() {
        return line1;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLine2() {
        return line2;
    }

    public String getLine3() {
        return line3;
    }

    public String getZip() {
        return zip;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.line1);
        dest.writeString(this.line2);
        dest.writeString(this.line3);
        dest.writeString(this.zip);
        dest.writeString(this.city);
        dest.writeString(this.region);
        dest.writeString(this.country);
    }

    public Address() {
    }

    protected Address(Parcel in) {
        this.line1 = in.readString();
        this.line2 = in.readString();
        this.line3 = in.readString();
        this.zip = in.readString();
        this.city = in.readString();
        this.region = in.readString();
        this.country = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
