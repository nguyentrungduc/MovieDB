package com.ptit.btl.moviedb.data.model.tv;

import com.ptit.btl.moviedb.data.model.Genre;
import com.ptit.btl.moviedb.data.model.person.Person;

import java.util.List;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public class Season {

    private String airDate;

    private String name;

    private int episodeCount;

    private String posterPath;

    private List<Episode> episodes;

    private String overview;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
