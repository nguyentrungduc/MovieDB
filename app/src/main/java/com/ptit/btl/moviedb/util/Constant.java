package com.ptit.btl.moviedb.util;

import android.support.annotation.StringDef;

import com.ptit.btl.moviedb.BuildConfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_GENRES;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIES_BY_GENRE;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIES_BY_PEOPLE;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIES_BY_PRODUCTION;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIES_WITH_CAST;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_CREDIT;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_DETAIL;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_GENRES;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_NOW_PLAYING;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_POPULAR;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_TOP_RATED;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_UPCOMING;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_PERSON_IMAGES;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_PERSON_INFORMATION;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_SEARCH_MOVIES;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_SEARCH_QUERY;
import static com.ptit.btl.moviedb.util.Constant.ApiUrlDef.API_URL_TRAILER_MOVIE;

/**
 * Created by admin on 25/4/18.
 */
public class Constant {
    static final int URL_REQUEST_TIMEOUT = 10000;
    static final int URL_CONNECT_TIMEOUT = 15000;
    public static final int SPLASH_TIMEOUT = 500;
    public static final int TEXT_OVERVIEW_MIN_LINES = 2;
    public static final int TEXT_OVERVIEW_MAX_LINES = 25;
    public static final String BUNDLE_MOVIE = "BUNDLE_MOVIE";
    public static final String BUNDLE_PEOPLE_ID = "BUNDLE_PEOPLE_ID";
    public static final String BUNDLE_PEOPLE_NAME = "BUNDLE_PEOPLE_NAME";
    public static final String BUNDLE_SEARCH_QUERY = "BUNDLE_SEARCH_QUERY";
    public static final String BUNDLE_TRAILER_KEY = "BUNDLE_TRAILER_KEY";
    public static final String BUNDLE_GENRE_ID = "BUNDLE_GENRE_ID";
    public static final String BUNDLE_GENRE_NAME = "BUNDLE_GENRE_NAME";
    private static final String API_KEY = "api_key=" + BuildConfig.API_KEY;
    public static final String API_KEY_YOUTUBE = BuildConfig.API_YOUTUBE_KEY;

    @StringDef({API_URL_MOVIE_POPULAR, API_URL_MOVIE_NOW_PLAYING,
        API_URL_MOVIE_UPCOMING, API_URL_MOVIE_TOP_RATED, API_URL_MOVIE_GENRES,
        API_URL_MOVIE_DETAIL, API_URL_MOVIE_CREDIT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ApiUrlDef {
        String API_URL_MOVIE_POPULAR = "movie/popular";
        String API_URL_MOVIE_NOW_PLAYING = "movie/now_playing";
        String API_URL_MOVIE_UPCOMING = "movie/upcoming";
        String API_URL_MOVIE_TOP_RATED = "movie/top_rated";
        String API_URL_MOVIE_GENRES = "genre/movie/list";
        String API_URL_MOVIE_DETAIL = "movie/";
        String API_URL_MOVIE_CREDIT = "movie/%s/credits?";
        String API_URL_MOVIES_BY_PEOPLE = "discover/movie?";
        String API_URL_MOVIES_BY_PRODUCTION = "company/%s/movies?";
        String API_URL_MOVIES_WITH_CAST = "&with_cast=%s";
        String API_URL_SEARCH_MOVIES = "search/movie?";
        String API_URL_SEARCH_QUERY = "&query=%s";
        String API_URL_TRAILER_MOVIE = "movie/%s/videos?";
        String API_URL_GENRES = "genre/movie/list?";
        String API_URL_MOVIES_BY_GENRE = "genre/%s/movies?";
        String API_URL_PERSON_INFORMATION = "person/%s?";
        String API_URL_PERSON_IMAGES = "person/%s/images?";
    }

    public static class ApiRequestUrl {
        private static final String API_URL = "https://api.themoviedb.org/3/";
        public static final String API_IMAGE_URL = "https://image.tmdb.org/t/p/w500/%s";
        public static final String API_URL_REQUEST = API_URL + "%s?"
            + API_KEY + "&language=%s&page=%s";
        public static final String API_PRODUCTION_BY_ID_REQUEST = API_URL
            + API_URL_MOVIE_DETAIL + "%s?" + API_KEY;
        public static final String API_CREDIT_BY_MOVIE_REQUEST = API_URL
            + API_URL_MOVIE_CREDIT + API_KEY;
        static final String API_REQUEST_METHOD = "GET";
        public static final String API_MOVIES_BY_PEOPLE_REQUEST = API_URL
            + API_URL_MOVIES_BY_PEOPLE + API_KEY + API_URL_MOVIES_WITH_CAST;
        public static final String API_MOVIES_BY_PRODUCTION_REQUEST = API_URL
            + API_URL_MOVIES_BY_PRODUCTION + API_KEY;
        public static final String API_MOVIES_BY_SEARCH = API_URL
            + API_URL_SEARCH_MOVIES + API_KEY + API_URL_SEARCH_QUERY;
        public static final String API_TRAILER_BY_MOVIE_ID = API_URL
            + API_URL_TRAILER_MOVIE + API_KEY;
        public static final String API_GENRES_REQUEST = API_URL
            + API_URL_GENRES + API_KEY;
        public static final String API_MOVIES_BY_GENRE_REQUEST = API_URL
            + API_URL_MOVIES_BY_GENRE + API_KEY;
        public static final String API_PERSON_INFO_REQUEST = API_URL
            + API_URL_PERSON_INFORMATION + API_KEY;
        public static final String API_PERSON_IMAGES_REQUEST = API_URL
                + API_URL_PERSON_IMAGES + API_KEY;

    }

    public static class ApiParameter {
        public static final String API_URL_LANGUAGE = "en-US";
    }

    static class ApiResultKey {
        static final String API_KEY_RESULTS = "results";
        static final String API_MOVIE_KEY_ID = "id";
        static final String API_MOVIE_KEY_TITLE = "title";
        static final String API_MOVIE_KEY_VOTE_AVERAGE = "vote_average";
        static final String API_MOVIE_KEY_POSTER_PATH = "poster_path";
        static final String API_MOVIE_KEY_BACKDROP_PATH = "backdrop_path";
        static final String API_MOVIE_KEY_OVERVIEW = "overview";
        static final String API_MOVIE_KEY_RELEASE_DATE = "release_date";
        static final String API_KEY_PRODUCTION_RESULTS = "production_companies";
        static final String API_PRODUCTION_KEY_ID = "id";
        static final String API_PRODUCTION_KEY_NAME = "name";
        static final String API_KEY_CREDIT_CAST = "cast";
        static final String API_KEY_CREDIT_CREW = "crew";
        static final String API_CREDIT_KEY_ID = "id";
        static final String API_CREDIT_KEY_CAST_ID = "cast_id";
        static final String API_CAST_KEY_CHARACTER = "character";
        static final String API_CAST_KEY_NAME = "name";
        static final String API_CAST_KEY_PROFILE_PATH = "profile_path";
        static final String API_CREW_CREDIT_ID = "credit_id";
        static final String API_CREW_DEPARTMENT = "department";
        static final String API_CREW_NAME = "name";
        static final String API_CREW_JOB = "job";
        static final String API_CREW_KEY_PROFILE_PATH = "profile_path";
        static final String API_TRAILER_ID = "id";
        static final String API_TRAILER_KEY = "key";
        static final String API_TRAILER_NAME = "name";
        static final String API_KEY_GENRES = "genres";
        static final String API_KEY_GENRES_ID = "id";
        static final String API_KEY_GENRES_NAME = "name";
        static final String API_PERSON_KEY_ID = "id";
        static final String API_PERSON_KEY_NAME = "name";
        static final String API_PERSON_KEY_AKA = "also_known_as";
        static final String API_PERSON_KEY_BIOGRAPHY = "biography";
        static final String API_PERSON_KEY_DATE_OF_BIRTH = "birthday";
        static final String API_PERSON_KEY_PLACE_OF_BIRTH = "place_of_birth";
        static final String API_PERSON_KEY_PROFILE_PICTURE = "profile_path";
        static final String API_PERSON_KEY_IMAGES = "file_path";
        static final String API_PERSON_KEY_IMAGES_CONTAINER = "profiles";
    }

    public static class MoviesDataBase {
        public static final String DATABASE_NAME = "moviesDatabase";
        public static final int DATABASE_VERSION = 1;
        //Table
        public static final String TABLE_FAVOURITE_MOVIES = "favouriteMovies";
        //Columns
        public static final String KEY_MOVIES_ID = "id";
        public static final String KEY_MOVIES_TITLE = "title";
        public static final String KEY_MOVIES_VOTE_AVERAGE = "voteAverage";
        public static final String KEY_MOVIES_POSTER_PATH = "posterPath";
        public static final String KEY_MOVIES_BACKDROP_PATH = "backdropPath";
        public static final String KEY_MOVIES_OVERVIEW = "overview";
        public static final String KEY_MOVIES_RELEASE_DATE = "releaseDate";
        //Query
        public static final String QUERY_CREATE_MOVIES = "CREATE TABLE " + TABLE_FAVOURITE_MOVIES +
            "(" +
            KEY_MOVIES_ID + " TEXT," +
            KEY_MOVIES_TITLE + " TEXT," +
            KEY_MOVIES_VOTE_AVERAGE + " TEXT," +
            KEY_MOVIES_POSTER_PATH + " TEXT," +
            KEY_MOVIES_BACKDROP_PATH + " TEXT," +
            KEY_MOVIES_OVERVIEW + " TEXT," +
            KEY_MOVIES_RELEASE_DATE + " TEXT" +
            ")";
        public static final String QUERY_DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";
        public static final String QUERY_SELECTION = " = ?";
    }
}
