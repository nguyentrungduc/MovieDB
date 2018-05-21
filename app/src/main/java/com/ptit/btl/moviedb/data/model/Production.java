package com.ptit.btl.moviedb.data.model;

/**
 * Created by admin on 25/4/18.
 */
public class Production {
    private String mId;
    private String mName;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    @Override
    public String toString() {
        return "Production{" +
                "mId='" + mId + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }
}
