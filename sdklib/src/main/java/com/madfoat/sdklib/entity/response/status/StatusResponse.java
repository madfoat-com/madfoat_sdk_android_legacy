package com.madfoat.sdklib.entity.response.status;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Hany on 5/11/2017.
 */
@Root(name = "Mobile", strict = false)
public class StatusResponse implements Parcelable {

    @Element
    private Auth auth;

//    @Element
//    private Authe auth;

    @Element
    private String trace;

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }



    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.auth, flags);
        dest.writeString(this.trace);
    }

    public StatusResponse() {
    }

    protected StatusResponse(Parcel in) {
        this.auth = in.readParcelable(Auth.class.getClassLoader());
        this.trace = in.readString();
    }

    public static final Creator<StatusResponse> CREATOR = new Creator<StatusResponse>() {
        @Override
        public StatusResponse createFromParcel(Parcel source) {
            return new StatusResponse(source);
        }

        @Override
        public StatusResponse[] newArray(int size) {
            return new StatusResponse[size];
        }
    };
}
