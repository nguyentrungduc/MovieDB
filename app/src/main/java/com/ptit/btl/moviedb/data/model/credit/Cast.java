package com.ptit.btl.moviedb.data.model.credit;

/**
 * Created by admin on 25/4/18.
 */
public class Cast {
    private String mId;
    private String mCastId;
    private String mCharacter;
    private String mName;
    private String mProfilePath;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getCastId() {
        return mCastId;
    }

    public void setCastId(String castId) {
        mCastId = castId;
    }

    public String getCharacter() {
        return mCharacter;
    }

    public void setCharacter(String character) {
        mCharacter = character;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "mId='" + mId + '\'' +
                ", mCastId='" + mCastId + '\'' +
                ", mCharacter='" + mCharacter + '\'' +
                ", mName='" + mName + '\'' +
                ", mProfilePath='" + mProfilePath + '\'' +
                '}';
    }
}
