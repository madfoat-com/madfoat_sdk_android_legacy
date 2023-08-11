package com.madfoat.sdklib.entity.response.payment;

import android.os.Parcel;
import android.os.Parcelable;


import com.madfoat.sdklib.entity.response.status.Auth;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by hany on 30/04/17.
 */

@Root(name = "Mobile", strict = false)
public class MobileResponse implements Parcelable {

    @Element(required = false)
    private Webview webview;
    /**
     * Commented by Divya on 06/08/2020.
     */
    @Element(required = false)
    private Auth auth;


//        @Element(required = false)
//    private Authe auth;

    @Element
    private String trace;

    public Webview getWebview() {
        return webview;
    }

    public String getTrace() {
        return trace;
    }

    public void setWebview(Webview webview) {
        this.webview = webview;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }


    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

//    public Authe getAuth() {
//        return auth;
//    }
//
//    public void setAuth(Authe auth) {
//        this.auth = auth;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.webview, flags);
        dest.writeParcelable(this.auth, flags);
        dest.writeString(this.trace);
    }

    public MobileResponse() {
    }

    protected MobileResponse(Parcel in) {
        this.webview = in.readParcelable(Webview.class.getClassLoader());
        this.auth = in.readParcelable(Webview.class.getClassLoader());
        this.trace = in.readString();
    }

    public static final Creator<MobileResponse> CREATOR = new Creator<MobileResponse>() {
        @Override
        public MobileResponse createFromParcel(Parcel source) {
            return new MobileResponse(source);
        }

        @Override
        public MobileResponse[] newArray(int size) {
            return new MobileResponse[size];
        }
    };
}
