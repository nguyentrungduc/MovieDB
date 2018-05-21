package com.ptit.btl.moviedb.data.model.tv;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public class Episode {
    private int number;

    private String name;

    private String overview;

    private float vote;

    private String overView;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getVote() {
        return vote;
    }

    public void setVote(float vote) {
        this.vote = vote;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", vote=" + vote +
                ", overView='" + overView + '\'' +
                '}';
    }
}
