package com.ptit.btl.moviedb.util;

/**
 * Created by ADMIN on 4/26/2018.
 */

public class StringUtils {
    public static String getURLAvatar(String id) {
        String url = "https://graph.facebook.com/" +
                id +
                "/picture?type=square";
        return url;

    }
}
