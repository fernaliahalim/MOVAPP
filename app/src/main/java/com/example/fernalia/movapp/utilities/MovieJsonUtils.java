package com.example.fernalia.movapp.utilities;

import android.content.Context;

import com.example.fernalia.movapp.DataObject;
import com.example.fernalia.movapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by Fernalia on 07/07/2017.
 */

public class MovieJsonUtils {

    public static ArrayList<DataObject> getSimpleMovieStringFromJson(Context context, String JsonStr) throws JSONException{
        ArrayList results = new ArrayList<DataObject>();

        //variable result json
        final String RESULT = "results";

        //variable which contain data
        final String MOVIE_ID            = "id";
        final String MOVIE_TITLE         = "title";
        final String MOVIE_POSTER_PATH   = "poster_path";
        final String MOVIE_POPULARITY    = "popularity";
        final String MOVIE_RELEASE_DATE  = "release_date";
        final String MOVIE_OVERVIEW      = "overview";
        final String MOVIE_BACKDROP_PATH = "backdrop_path";
        final String MOVIE_VOTE_AVERANGE = "vote_average";

        JSONObject json = new JSONObject(JsonStr);

        JSONArray jsonArray = json.getJSONArray(RESULT);

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            DataObject obj = new DataObject(jsonObject.getString(MOVIE_ID), jsonObject.getString(MOVIE_POSTER_PATH),  jsonObject.getString(MOVIE_TITLE), jsonObject.getString(MOVIE_POPULARITY), jsonObject.getString(MOVIE_RELEASE_DATE), jsonObject.getString(MOVIE_OVERVIEW), jsonObject.getString(MOVIE_BACKDROP_PATH), jsonObject.getString(MOVIE_VOTE_AVERANGE));
            results.add(i, obj);
        }

        return results;
    }

    public static ArrayList<DataObject> getTrailerMovieJson(Context context, String JsonStr) throws JSONException{
        ArrayList results = new ArrayList<DataObject>();

        //variable result json
        final String RESULT = "results";

        //variable which contain data
        final String TRAILER_KEY  = "key";
        final String TRAILER_NAME = "name";

        JSONObject json = new JSONObject(JsonStr);

        JSONArray jsonArray = json.getJSONArray(RESULT);

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            DataObject obj = new DataObject(jsonObject.getString(TRAILER_KEY), jsonObject.getString(TRAILER_NAME));
            results.add(i, obj);
        }

        return results;
    }

    public static ArrayList<DataObject> getReviewMovieJson(Context context, String JsonStr) throws JSONException{
        ArrayList results = new ArrayList<DataObject>();

        //variable result json
        final String RESULT = "results";

        //variable which contain data
        final String REVIEW_AUTHOR  = "author";
        final String REVIEW_CONTENT = "content";
        final String REVIEW_URL     = "url";

        JSONObject json = new JSONObject(JsonStr);

        JSONArray jsonArray = json.getJSONArray(RESULT);

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            DataObject obj = new DataObject(jsonObject.getString(REVIEW_AUTHOR), jsonObject.getString(REVIEW_CONTENT), jsonObject.getString(REVIEW_URL));
            results.add(i, obj);
        }

        return results;
    }
}