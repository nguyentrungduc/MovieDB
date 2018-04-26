package com.ptit.btl.moviedb.data.model;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class User {
    private String mId;
    private String mUserName;
    private String mImageLink;
    private String mEmail;
    private List<Movie> mMovieList;

    public User() {
    }

    public User(String id, String userName, String imageLink) {
        mId = id;
        mUserName = userName;
        mImageLink = imageLink;
    }

    public User(String id, String userName, String imageLink, List<Movie> movieList) {
        mId = id;
        mUserName = userName;
        mImageLink = imageLink;
        mMovieList = movieList;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getImageLink() {
        return mImageLink;
    }

    public void setImageLink(String imageLink) {
        mImageLink = imageLink;
    }

    public List<Movie> getMovieList() {
        return mMovieList;
    }

    public void setMovieList(List<Movie> movieList) {
        mMovieList = movieList;
    }
}
