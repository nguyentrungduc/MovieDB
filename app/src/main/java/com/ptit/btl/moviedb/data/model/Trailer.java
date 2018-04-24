package com.ptit.btl.moviedb.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 25/4/18.
 */
public class Trailer implements Parcelable {
    private String mId;
    private String mKey;
    private String mName;

    public Trailer() {
    }

    private Trailer(Parcel in) {
        mId = in.readString();
        mKey = in.readString();
        mName = in.readString();
    }

    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mKey);
        parcel.writeString(mName);
    }
}
