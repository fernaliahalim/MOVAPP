package com.example.fernalia.movapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Ferna on 29/11/2016.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {

        ImageView image;
        TextView label;

        public DataObjectHolder(View itemView) {
            super(itemView);
            image        = (ImageView) itemView.findViewById(R.id.imageView);
            label        = (TextView) itemView.findViewById(R.id.text_rated);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, final int position) {
        final int posisi = position;

        holder.label.setText(mDataset.get(position).getRated());

        Context context = holder.image.getContext();
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500/" + mDataset.get(position).getDrawable())
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DetailActivity.class);
                i.putExtra("movie_id", mDataset.get(position).getId());
                i.putExtra("movie_title", mDataset.get(position).getTitle());
                i.putExtra("movie_poster_path", mDataset.get(position).getDrawable());
                i.putExtra("movie_popularity", mDataset.get(position).getPopularity());
                i.putExtra("movie_release_date", mDataset.get(position).getRelease_date());
                i.putExtra("movie_overview", mDataset.get(position).getOverview());
                i.putExtra("movie_backdrop_path", mDataset.get(position).getBackdrop_path());
                i.putExtra("movie_vote_average", mDataset.get(position).getRated());
                v.getContext().startActivity(i);
            }
        });
    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
