package com.madfoat.sdklib.entity.request.payment;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Divya on 30/05/20.
 */
@Root(name = "Mobile", strict = false)
public class MobileRequest implements Parcelable {

    @Element
    private String store;
    @Element
    private String key;
    @Element
    private String framed;
    @Element
    private Device device;
    @Element
    private App app;
    @Element
    private Tran tran;
    @Element
    private Billing billing;

    @Element
    private String custref;

    public String getStore() {
        return store;
    }

    public String getKey() {
        return key;
    }

    public String getFramed() {
        return framed;
    }

    public void setFramed(String framed) {
        this.framed = framed;
    }

    public Device getDevice() {
        return device;
    }

    public App getApp() {
        return app;
    }

    public Tran getTran() {
        return tran;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void setTran(Tran tran) {
        this.tran = tran;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public String getCustref() {
        return custref;
    }

    public void setCustref(String custref) {
        this.custref = custref;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.store);
        dest.writeString(this.key);
        dest.writeString(this.framed);
        dest.writeParcelable(this.device, flags);
        dest.writeParcelable(this.app, flags);
        dest.writeParcelable(this.tran, flags);
        dest.writeParcelable(this.billing, flags);
        dest.writeString(this.custref);
    }

    public MobileRequest() {
    }

    protected MobileRequest(Parcel in) {
        this.store = in.readString();
        this.key = in.readString();
        this.framed=in.readString();
        this.device = in.readParcelable(Device.class.getClassLoader());
        this.app = in.readParcelable(App.class.getClassLoader());
        this.tran = in.readParcelable(Tran.class.getClassLoader());
        this.billing = in.readParcelable(Billing.class.getClassLoader());
        this.custref=in.readString();
    }

    public static final Creator<MobileRequest> CREATOR = new Creator<MobileRequest>() {
        @Override
        public MobileRequest createFromParcel(Parcel source) {
            return new MobileRequest(source);
        }

        @Override
        public MobileRequest[] newArray(int size) {
            return new MobileRequest[size];
        }
    };




//    @Override
//    public String toString() {
//        return "MobileRequest{" +
//                "store='" + store + '\'' +
//                ", key='" + key + '\'' +
//                ", device=" + device +
//                ", app=" + app +
//                ", tran=" + tran +
//                ", billing=" + billing +
//                ", custref='" + custref + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "MobileRequest{" +
                "store='" + store + '\'' +
                ", key='" + key + '\'' +
                ", framed='" + framed + '\'' +
                ", device=" + device +
                ", app=" + app +
                ", tran=" + tran +
                ", billing=" + billing +
                ", custref='" + custref + '\'' +
                '}';
    }
}
