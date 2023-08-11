package com.madfoat.sdklib.entity.response.status;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name="card",strict = false)
public class Card implements Parcelable {
    @Element
    private String code;

    @Element
    private String last4;

    @Element
    private String country;

    @Element
    private String first6;

    @Element
    private Expiry expiry;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirst6() {
        return first6;
    }

    public void setFirst6(String first6) {
        this.first6 = first6;
    }

    public Expiry getExpiry() {
        return expiry;
    }

    public void setExpiry(Expiry expiry) {
        this.expiry = expiry;
    }

public Card(){

}
//    @Override
//    public String toString() {
//        return "Card{" +
//                "code='" + code + '\'' +
//                ", last4='" + last4 + '\'' +
//                ", country='" + country + '\'' +
//                ", first6='" + first6 + '\'' +
//                ", expiry=" + expiry.toString() +
//                '}';
//    }


//    @Override
//    public String toString() {
//        return "Card{" +
//                "code='" + code + '\'' +
//                ", last4='" + last4 + '\'' +
//                ", country='" + country + '\'' +
//                ", first6='" + first6 + '\'' +
//                ", expiry=" + expiry +
//                '}';
//    }
    protected Card(Parcel in) {
        this.code=in.readString();
        this.last4=in.readString();
        this.country=in.readString();
        this.first6=in.readString();
        this.expiry = in.readParcelable(Expiry.class.getClassLoader());
        //  this.expiry = in.readParcelable(Webview.class.getClassLoader());
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.last4);
        dest.writeString(this.country);
        dest.writeString(this.first6);

        dest.writeParcelable(this.expiry, flags);

    }
}
