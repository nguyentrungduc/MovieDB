package com.ptit.btl.moviedb.data.source.local.sharedprf;

/**
 * Created by admin on 25/4/18.
 */

public interface SharedPrefsApi {
    <T> T get(String key, Class<T> clazz);

    <T> T get(String key, Class<T> clazz, T defaultValue);

    <T> void put(String key, T data);

    void delete(String key);

    void clear();
}
