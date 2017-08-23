package com.example.fernalia.movapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fernalia.movapp.utilities.MovieJsonUtils;
import com.example.fernalia.movapp.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    //RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    String movie_id    = "";
    String trailer_key = "";

    ImageView image_id;
    ImageView image_trailer;
    TextView text_title;
    TextView text_desc;
    TextView text_release_date;
    TextView text_detail_rated;
    TextView text_detail_popularity;
    TextView text_trailer_name;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        image_id               = (ImageView) rootView.findViewById(R.id.image_id);
        image_trailer          = (ImageView) rootView.findViewById(R.id.image_trailer);
        text_title             = (TextView) rootView.findViewById(R.id.txt_title);
        text_desc              = (TextView) rootView.findViewById(R.id.txt_desc);
        text_release_date      = (TextView) rootView.findViewById(R.id.txt_release_date);
        text_detail_rated      = (TextView) rootView.findViewById(R.id.txt_detail_rated);
        text_detail_popularity = (TextView) rootView.findViewById(R.id.txt_detail_popularity);
        text_trailer_name      = (TextView) rootView.findViewById(R.id.txt_trailer);


        Intent i = getActivity().getIntent();

        Bundle extras = i.getExtras();
        if(extras != null){
            movie_id = i.getStringExtra("movie_id");

            Picasso.with(getActivity())
                    .load("https://image.tmdb.org/t/p/w500/" + i.getStringExtra("movie_backdrop_path"))
                    .into(image_id);
            text_title.setText(i.getStringExtra("movie_title"));
            text_desc.setText(i.getStringExtra("movie_overview"));
            text_release_date.setText(i.getStringExtra("movie_release_date"));
            text_detail_rated.setText(i.getStringExtra("movie_vote_average"));
            text_detail_popularity.setText(i.getStringExtra("movie_popularity"));
        }

        image_trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailer_key));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + trailer_key));
                try {
                    startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    startActivity(webIntent);
                }
            }
        });

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_review);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);

        loadTrilerData();

        return rootView;
    }

    private void loadTrilerData(){
        new DetailActivityFragment.TrailerMovieTask().execute();
        new DetailActivityFragment.ReviewMovieTask().execute();
    }

    public class TrailerMovieTask extends AsyncTask<String, Void, ArrayList<DataObject>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<DataObject> doInBackground(String... params){
            URL trailerRequestUrl = NetworkUtils.buildUrl(movie_id + "/videos", 1, getActivity().getString(R.string.THE_MOVIE_DB_API_TOKEN));

            try {
                String jsonResponse = NetworkUtils.getResponseFromHttpUrl(trailerRequestUrl);

                ArrayList<DataObject> simpleJsonData = MovieJsonUtils.getTrailerMovieJson(getActivity(), jsonResponse);

                return simpleJsonData;
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<DataObject> dataObjects){

            if(dataObjects != null && dataObjects.size() > 0){
                trailer_key  = dataObjects.get(dataObjects.size()-1).getTrailer_key();
                text_trailer_name.setText(dataObjects.get(dataObjects.size()-1).getTrailer_name());
            } else{
            }
        }
    }

    public class ReviewMovieTask extends AsyncTask<String, Void, ArrayList<DataObject>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<DataObject> doInBackground(String... params){
            URL reviewRequestUrl = NetworkUtils.buildUrl(movie_id + "/reviews", 1, getActivity().getString(R.string.THE_MOVIE_DB_API_TOKEN));

            try {
                String jsonResponse = NetworkUtils.getResponseFromHttpUrl(reviewRequestUrl);

                ArrayList<DataObject> simpleJsonData = MovieJsonUtils.getReviewMovieJson(getActivity(), jsonResponse);

                return simpleJsonData;
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<DataObject> dataObjects){

            if(dataObjects != null && dataObjects.size() > 0){
                mAdapter = new MyRecyclerViewReviewAdapter(dataObjects);
                mRecyclerView.setAdapter(mAdapter);
            } else{
            }
        }
    }
}
