package com.example.fernalia.movapp.data;

import android.provider.BaseColumns;

/**
 * Created by Fernalia on 07/08/2017.
 */

public class FavoriteMovie {

    public static final class FavoriteMovieEntry implements BaseColumns{
        public static final String TABLE_NAME         = "favorite_movie";
        public static final String COLUMN_MOVIE_ID    = "movie_id";
        public static final String COLUMN_MOVIE_TITLE = "movie_title";
    }
}
