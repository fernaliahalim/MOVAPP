package com.example.fernalia.movapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.fernalia.movapp.data.FavoriteMovie;
import com.example.fernalia.movapp.data.FavoriteMovieDBHelper;

public class DetailActivity extends AppCompatActivity {

    private SQLiteDatabase mDb;

    public static String movie_id;
    public static String movie_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create a DB helper (this will create the DB if run for the first time)
        FavoriteMovieDBHelper dbHelper = new FavoriteMovieDBHelper(this);

        // Keep a reference to the mDb until paused or killed. Get a writable database
        // because you will be adding data
        mDb = dbHelper.getWritableDatabase();

        Intent i = getIntent();
        movie_id    = i.getStringExtra("movie_id");
        movie_title = i.getStringExtra("movie_title");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // COMPLETED (5) Inside, create a ContentValues instance to pass the values onto the insert query
                ContentValues cv = new ContentValues();
                // COMPLETED (6) call put to insert the name value with the key COLUMN_GUEST_NAME
                cv.put(FavoriteMovie.FavoriteMovieEntry.COLUMN_MOVIE_ID, movie_id);
                // COMPLETED (7) call put to insert the party size value with the key COLUMN_PARTY_SIZE
                cv.put(FavoriteMovie.FavoriteMovieEntry.COLUMN_MOVIE_TITLE, movie_title);

                Cursor cursor = mDb.rawQuery("SELECT " + FavoriteMovie.FavoriteMovieEntry.COLUMN_MOVIE_ID + " FROM " + FavoriteMovie.FavoriteMovieEntry.TABLE_NAME + " WHERE " + FavoriteMovie.FavoriteMovieEntry.COLUMN_MOVIE_ID + " = '" + movie_id + "' ", null);

                if(cursor.getString(cursor.getColumnIndexOrThrow("movie_id")) != ""){

                } else{
                    mDb.insert(FavoriteMovie.FavoriteMovieEntry.TABLE_NAME, null, cv);
                }

                Snackbar.make(view, "Add to favorite movie", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
