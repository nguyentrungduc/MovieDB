package com.ptit.btl.moviedb.data.model.tv;

import com.ptit.btl.moviedb.data.model.Genre;
import com.ptit.btl.moviedb.data.model.Production;
import com.ptit.btl.moviedb.data.model.person.Person;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public class TvSeries implements Serializable{

    private double voteAverage;

    private String backDropPath;

    private List<Person> createBy;

    private String firstAirDate;

    private List<Genre> genre;

    private String homepage;

    private int id;

    private String name;

    private List<Season> seasons;

    private String overview;

    private String posterPath;

    private String type;

    private List<Production> productions;

    public String getBackDropPath() {
        return backDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
    }

    public List<Person> getCreateBy() {
        return createBy;
    }

    public void setCreateBy(List<Person> createBy) {
        this.createBy = createBy;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setProductions(List<Production> productions) {
        this.productions = productions;
    }

    @Override
    public String toString() {
        return "TvSeries{" +
                "voteAverage=" + voteAverage +
                ", backDropPath='" + backDropPath + '\'' +
                ", createBy=" + createBy +
                ", firstAirDate='" + firstAirDate + '\'' +
                ", genre=" + genre +
                ", homepage='" + homepage + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", seasons=" + seasons +
                ", overview='" + overview + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
