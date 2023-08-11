package com.madfoat.sdklib.entity.response.payment;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by hany on 30/04/17.
 */
@Root(strict = false)
public class Webview implements Parcelable {

    @Element
    private String start;
    @Element
    private String close;
    @Element
    private String abort;
    @Element
    private String code;

    public String getStart() {
        return start;
    }

    public String getClose() {
     return close;
    }

    public String getAbort() {
        return abort;
    }

    public String getCode() {
        return code;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public void setAbort(String abort) {
        this.abort = abort;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.start);
       // dest.writeString(this.close);
        dest.writeString(this.abort);
        dest.writeString(this.code);
    }

    public Webview() {
    }

    protected Webview(Parcel in) {
        this.start = in.readString();
        this.close = in.readString();
        this.abort = in.readString();
        this.code = in.readString();
    }

    public static final Creator<Webview> CREATOR = new Creator<Webview>() {
        @Override
        public Webview createFromParcel(Parcel source) {
            return new Webview(source);
        }

        @Override
        public Webview[] newArray(int size) {
            return new Webview[size];
        }
    };
}
