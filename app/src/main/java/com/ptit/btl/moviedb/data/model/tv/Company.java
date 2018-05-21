package com.ptit.btl.moviedb.data.model.tv;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public class Company {
    private int id;

    private String logoPath;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
