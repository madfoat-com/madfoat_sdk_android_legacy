package com.madfoat.sdklib.entity.request.payment;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by hany on 30/04/17.
 */

@Root(strict = false)
public class Device implements Parcelable {

    @Element
    private String type;
    @Element(required = false)
    private String id;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.id);
    }

    public Device() {
    }

    protected Device(Parcel in) {
        this.type = in.readString();
        this.id = in.readString();
    }

    public static final Creator<Device> CREATOR = new Creator<Device>() {
        @Override
        public Device createFromParcel(Parcel source) {
            return new Device(source);
        }

        @Override
        public Device[] newArray(int size) {
            return new Device[size];
        }
    };

    @Override
    public String toString()
    {
        return "Device{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
