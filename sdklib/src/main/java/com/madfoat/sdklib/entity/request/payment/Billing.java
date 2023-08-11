package com.madfoat.sdklib.entity.request.payment;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by hany on 30/04/17.
 */

@Root(strict = false)
public class Billing implements Parcelable {

    @Element
    private Name name;
    @Element
    private Address address;
    @Element
    private String email;

    @Element
    private String phone;

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone(String phone) {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.name);
        dest.writeParcelable(this.address, flags);
        dest.writeString(this.email);
        dest.writeString(this.phone);
    }

    public Billing() {
    }

    protected Billing(Parcel in) {
        this.name = (Name) in.readSerializable();
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.email = in.readString();
        this.phone=in.readString();
    }

    public static final Creator<Billing> CREATOR = new Creator<Billing>() {
        @Override
        public Billing createFromParcel(Parcel source) {
            return new Billing(source);
        }

        @Override
        public Billing[] newArray(int size) {
            return new Billing[size];
        }
    };

//    @Override
//    public String toString()
//    {
//        return "Billing{" +
//                "name=" + name +
//                ", address=" + address +
//                ", email='" + email + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "Billing{" +
                "name=" + name +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
