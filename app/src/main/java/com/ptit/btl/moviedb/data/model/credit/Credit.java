package com.ptit.btl.moviedb.data.model.credit;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class Credit {
    private String mId;
    private List<Cast> mCasts;
    private List<Crew> mCrews;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public List<Cast> getCasts() {
        return mCasts;
    }

    public void setCasts(List<Cast> casts) {
        mCasts = casts;
    }

    public List<Crew> getCrews() {
        return mCrews;
    }

    public void setCrews(List<Crew> crews) {
        mCrews = crews;
    }
}
