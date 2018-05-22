package com.ptit.btl.moviedb.util;

import android.util.Log;

import com.ptit.btl.moviedb.data.model.Genre;
import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.model.Production;
import com.ptit.btl.moviedb.data.model.Trailer;
import com.ptit.btl.moviedb.data.model.User;
import com.ptit.btl.moviedb.data.model.credit.Cast;
import com.ptit.btl.moviedb.data.model.credit.Credit;
import com.ptit.btl.moviedb.data.model.credit.Crew;
import com.ptit.btl.moviedb.data.model.person.Person;
import com.ptit.btl.moviedb.data.model.tv.Review;
import com.ptit.btl.moviedb.data.model.tv.Season;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by admin on 25/4/18.
 */
class RequestAPIUtils {
    static String getJsonStringFromUrl(String urlString) throws IOException {
        HttpsURLConnection urlConnection;
        URL url = new URL(urlString);
        urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod(Constant.ApiRequestUrl.API_REQUEST_METHOD);
        urlConnection.setReadTimeout(Constant.URL_REQUEST_TIMEOUT);
        urlConnection.setConnectTimeout(Constant.URL_CONNECT_TIMEOUT);
        urlConnection.setDoOutput(true);
        urlConnection.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        String jsonString = sb.toString();
        urlConnection.disconnect();
        return jsonString;
    }

    static List<Movie> parseJsonToMovies(String json) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray movieJsonArray = jsonObject.getJSONArray(
            Constant.ApiResultKey.API_KEY_RESULTS);
        for (int i = 0; i < movieJsonArray.length(); i++) {
            Movie movie = new Movie();
            movie.setId(movieJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_MOVIE_KEY_ID));
            movie.setTitle(movieJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_MOVIE_KEY_TITLE));
            movie.setPosterPath(movieJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_MOVIE_KEY_POSTER_PATH));
            movie.setVoteAverage(movieJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_MOVIE_KEY_VOTE_AVERAGE));
            movie.setBackdropPath(movieJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_MOVIE_KEY_BACKDROP_PATH));
            movie.setOverview(movieJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_MOVIE_KEY_OVERVIEW));
            movie.setReleaseDate(movieJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_MOVIE_KEY_RELEASE_DATE));
            movies.add(movie);
        }
        return movies;
    }

    static List<Production> parseJsonToProductions(String json)
        throws JSONException {
        List<Production> productions = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray productionJsonArray = jsonObject.getJSONArray(
            Constant.ApiResultKey.API_KEY_PRODUCTION_RESULTS);
        for (int i = 0; i < productionJsonArray.length(); i++) {
            Production production = new Production();
            production.setId(productionJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_PRODUCTION_KEY_ID));
            production.setName(productionJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_PRODUCTION_KEY_NAME));
            productions.add(production);
        }
        return productions;
    }

    static User parseJsonToUser(String json)
            throws JSONException {
        User user = new User();
        JSONObject jsonObject = new JSONArray(json).getJSONObject(0);

        user.setUserName(jsonObject.getString("username"));
        user.setImageLink(jsonObject.getString("image_url"));
        return user;
    }

    static Credit parseJsonToCredit(String json) throws JSONException {
        Credit credit = new Credit();
        List<Cast> casts = new ArrayList<>();
        List<Crew> crews = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        credit.setId(jsonObject.getString(
            Constant.ApiResultKey.API_CREDIT_KEY_ID));
        getCastFromCredit(casts, jsonObject);
        getCrewFromCredit(crews, jsonObject);
        credit.setCasts(casts);
        credit.setCrews(crews);
        return credit;
    }

    private static void getCrewFromCredit(List<Crew> crews, JSONObject jsonObject)
        throws JSONException {
        JSONArray crewJsonArray = jsonObject.getJSONArray(
            Constant.ApiResultKey.API_KEY_CREDIT_CREW);
        for (int i = 0; i < crewJsonArray.length(); i++) {
            Crew crew = new Crew();
            crew.setId(crewJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_CREDIT_KEY_ID));
            crew.setCreditId(crewJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_CREW_CREDIT_ID));
            crew.setDepartment(crewJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_CREW_DEPARTMENT));
            crew.setName(crewJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_CREW_NAME));
            crew.setJob(crewJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_CREW_JOB));
            crew.setProfilePath(crewJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_CREW_KEY_PROFILE_PATH));
            crews.add(crew);
        }
    }

    private static void getCastFromCredit(List<Cast> casts, JSONObject jsonObject)
        throws JSONException {
        JSONArray castJsonArray = jsonObject.getJSONArray(
            Constant.ApiResultKey.API_KEY_CREDIT_CAST);
        for (int i = 0; i < castJsonArray.length(); i++) {
            Cast cast = new Cast();
            cast.setId(castJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_CREDIT_KEY_ID));
            cast.setCastId(castJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_CREDIT_KEY_CAST_ID));
            cast.setCharacter(castJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_CAST_KEY_CHARACTER));
            cast.setProfilePath(castJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_CAST_KEY_PROFILE_PATH));
            cast.setName(castJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_CAST_KEY_NAME));
            casts.add(cast);
        }
    }

    static List<Trailer> parseJsonToTrailer(String json) throws JSONException {
        List<Trailer> trailers = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray movieJsonArray = jsonObject.getJSONArray(
            Constant.ApiResultKey.API_KEY_RESULTS);
        for (int i = 0; i < movieJsonArray.length(); i++) {
            Trailer trailer = new Trailer();
            trailer.setId(movieJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_TRAILER_ID));
            trailer.setKey(movieJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_TRAILER_KEY));
            trailer.setName(movieJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_TRAILER_NAME));
            trailers.add(trailer);
        }
        return trailers;
    }

    static List<Genre> parseJsonToGenre(String json) throws JSONException {
        List<Genre> genres = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray genreJsonArray = jsonObject.getJSONArray(
            Constant.ApiResultKey.API_KEY_GENRES);
        for (int i = 0; i < genreJsonArray.length(); i++) {
            Genre genre = new Genre();
            genre.setId(genreJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_KEY_GENRES_ID));
            genre.setName(genreJsonArray.getJSONObject(i)
                .getString(Constant.ApiResultKey.API_KEY_GENRES_NAME));
            genres.add(genre);
        }
        return genres;
    }

    static Person parseJsonToPerson(String json) throws JSONException {
        Log.d("parseJsonToPerson", json);
        JSONObject jsonObject = new JSONObject(json);
        Person person = new Person();
        person.setId(jsonObject.getInt(Constant.ApiResultKey.API_PERSON_KEY_ID));
        person.setName(jsonObject.getString(Constant.ApiResultKey.API_PERSON_KEY_NAME));
        person.setBiography(jsonObject.getString(Constant.ApiResultKey.API_PERSON_KEY_BIOGRAPHY));
        List<String> akas = new ArrayList<>();
        JSONArray array = jsonObject.getJSONArray(Constant.ApiResultKey.API_PERSON_KEY_AKA);
        for (int i = 0; i < array.length(); i++) {
            akas.add(array.get(i).toString());
        }
        person.setAka(akas);
        person.setBirthDay(jsonObject.getString(Constant.ApiResultKey.API_PERSON_KEY_DATE_OF_BIRTH));
        person.setPlaceOfBirth(jsonObject.getString(Constant.ApiResultKey.API_PERSON_KEY_PLACE_OF_BIRTH));
        person.setProfilePicturePath(jsonObject.getString(Constant.ApiResultKey.API_PERSON_KEY_PROFILE_PICTURE));
        return person;
    }

    static List<String> parseJsonToImages(String json) throws JSONException {
        JSONObject container = new JSONObject(json);
        JSONArray array = container.getJSONArray(Constant.ApiResultKey.API_PERSON_KEY_IMAGES_CONTAINER);
        List<String> images = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            images.add(object.getString(Constant.ApiResultKey.API_PERSON_KEY_IMAGES));
        }
        return images;
    }

    static List<TvSeries> parseJsonToTv(String json) throws JSONException {
        JSONObject container = new JSONObject(json);
        JSONArray results = container.getJSONArray("results");
        List<TvSeries> tvSeries = new ArrayList<>();
        for (int i = 0; i < results.length(); i++) {
            TvSeries tv = new TvSeries();
            tv.setName(results.getJSONObject(i).getString("original_name"));
            tv.setPosterPath(results.getJSONObject(i).getString("poster_path"));
            tv.setVoteAverage(results.getJSONObject(i).getDouble("vote_average"));
            tv.setId(results.getJSONObject(i).getInt("id"));

            tvSeries.add(tv);
        }
        return tvSeries;
    }

    static TvSeries parseJsonToTvDetail(String json) throws JSONException {
        JSONObject container = new JSONObject(json);
        TvSeries tvSeries = new TvSeries();
        String backdropPath = container.getString("backdrop_path");
        tvSeries.setBackDropPath(backdropPath);

        JSONArray createdByJSON = container.getJSONArray("created_by");
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < createdByJSON.length(); i++) {
            JSONObject jsonObject = createdByJSON.getJSONObject(i);
            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            String profilePath = jsonObject.getString("profile_path");

            Person person = new Person();
            person.setId(id);
            person.setName(name);
            person.setProfilePicturePath(profilePath);

            people.add(person);
        }
        tvSeries.setCreateBy(people);

        String firstAirDate = container.getString("first_air_date");
        tvSeries.setFirstAirDate(firstAirDate);

        JSONArray genreJSON = container.getJSONArray("genres");
        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < genreJSON.length(); i++) {
            JSONObject object = genreJSON.getJSONObject(i);
            String name = object.getString("name");
            int id = object.getInt("id");

            Genre genre = new Genre();
            genre.setName(name);

            genres.add(genre);
        }
        tvSeries.setGenre(genres);

        int id = container.getInt("id");
        tvSeries.setId(id);

        String name = container.getString("name");
        tvSeries.setName(name);

        String type = container.getString("type");
        tvSeries.setType(type);

        String overview = container.getString("overview");
        tvSeries.setOverview(overview);

        JSONArray productionJson = container.getJSONArray("production_companies");
        List<Production> productions = new ArrayList<>();
        for (int i = 0; i < genreJSON.length(); i++) {
            JSONObject object = productionJson.getJSONObject(i);
            String mName = object.getString("name");

            Production production = new Production();
            production.setName(mName);

            productions.add(production);
        }
        tvSeries.setProductions(productions);

        return tvSeries;
    }

    static List<Review> parseJsonToReviewList(String json) throws JSONException {
        JSONObject container = new JSONObject(json);
        JSONArray results = container.getJSONArray("results");

        List<Review> reviews = new ArrayList<>();

        for (int i = 0; i < results.length(); i++) {
            Review review = new Review();
            String author = results.getJSONObject(i).getString("author");
            String content = results.getJSONObject(i).getString("content");
            review.setAuthor(author);
            review.setContent(content);

            reviews.add(review);
        }

        return reviews;
    }

    static List<Season> parseJsonToSeason(String json) throws JSONException {
        JSONObject container = new JSONObject(json);
        JSONArray seasonJSON = container.getJSONArray("seasons");
        List<Season> seasons = new ArrayList<>();
        for (int i = 0; i < seasonJSON.length(); i++) {
            Season season = new Season();
            season.setAirDate(seasonJSON.getJSONObject(i).getString("air_date"));
            season.setEpisodeCount(seasonJSON.getJSONObject(i).getInt("episode_count"));
            season.setName(seasonJSON.getJSONObject(i).getString("name"));
            season.setPosterPath(seasonJSON.getJSONObject(i).getString("poster_path"));
            season.setOverview(seasonJSON.getJSONObject(i).getString("overview"));

            seasons.add(season);
        }

        return seasons;
    }
}
