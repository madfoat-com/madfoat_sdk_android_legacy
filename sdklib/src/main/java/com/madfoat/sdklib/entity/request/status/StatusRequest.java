package com.madfoat.sdklib.entity.request.status;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Dell on 5/11/2017.
 */

@Root(name="Mobile", strict = false)
public class StatusRequest implements Parcelable {

    @Element
    private String store;
    @Element
    private String key;
    @Element
    private String complete;




    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public StatusRequest() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.store);
        dest.writeString(this.key);
        dest.writeString(this.complete);
    }

    protected StatusRequest(Parcel in) {
        this.store = in.readString();
        this.key = in.readString();
        this.complete = in.readString();
    }

    public static final Creator<StatusRequest> CREATOR = new Creator<StatusRequest>() {
        @Override
        public StatusRequest createFromParcel(Parcel source) {
            return new StatusRequest(source);
        }

        @Override
        public StatusRequest[] newArray(int size) {
            return new StatusRequest[size];
        }
    };
}
