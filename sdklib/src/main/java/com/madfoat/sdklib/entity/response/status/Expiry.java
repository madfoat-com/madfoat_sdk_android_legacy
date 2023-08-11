package com.madfoat.sdklib.entity.response.status;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Expiry  implements Parcelable {

    @Element
    private String month;

    @Element
    private String year;

    protected Expiry(Parcel in) {
            month = in.readString();
            year = in.readString();
    }

 public Expiry(){

}
    public static final Creator<Expiry> CREATOR = new Creator<Expiry>() {
        @Override
        public Expiry createFromParcel(Parcel in) {
            return new Expiry(in);
        }

        @Override
        public Expiry[] newArray(int size) {
            return new Expiry[size];
        }
    };

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(month);
        dest.writeString(year);
    }


//    @Override
//    public String toString() {
//        return "Expiry{" +
//                "month='" + month + '\'' +
//                ", year='" + year + '\'' +
//                '}';
//    }
}
