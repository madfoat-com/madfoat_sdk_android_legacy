package com.madfoat.sdklib.entity.request.payment;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(strict = false)
public class App implements Parcelable {

    @Element
    private String name;
    @Element
    private String version;
    @Element
    private String user;
    @Element
    private String id;
    @Element
    private String sdk;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getUser() {
        return user;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.version);
        dest.writeString(this.user);
        dest.writeString(this.id);
        dest.writeString(this.sdk);
    }

    public App() {
    }

    protected App(Parcel in) {
        this.name = in.readString();
        this.version = in.readString();
        this.user = in.readString();
        this.id = in.readString();
        this.sdk = in.readString();

    }

    public static final Creator<App> CREATOR = new Creator<App>() {
        @Override
        public App createFromParcel(Parcel source) {
            return new App(source);
        }

        @Override
        public App[] newArray(int size) {
            return new App[size];
        }
    };

    @Override
    public String toString()
    {
        return "App{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", user='" + user + '\'' +
                ", id='" + id + '\'' +
                ", sdk='" + sdk + '\'' +
                '}';
    }
}
