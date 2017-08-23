package com.example.fernalia.movapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Fernalia on 07/08/2017.
 */

public class MyRecyclerViewReviewAdapter extends  RecyclerView.Adapter<MyRecyclerViewReviewAdapter.DataObjectHolder>{
    private static String LOG_TAG = "MyRecyclerViewReviewAdapter";
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {

        TextView txt_reviewer_name;
        TextView txt_reviewer_content;

        public DataObjectHolder(View itemView) {
            super(itemView);
            txt_reviewer_name    = (TextView) itemView.findViewById(R.id.txt_reviewer_name);
            txt_reviewer_content = (TextView) itemView.findViewById(R.id.txt_reviewer_content);
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

    public MyRecyclerViewReviewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyRecyclerViewReviewAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_review_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewReviewAdapter.DataObjectHolder holder, int position) {
        final int posisi = position;

        holder.txt_reviewer_name.setText(mDataset.get(position).getReview_author());
        holder.txt_reviewer_content.setText(mDataset.get(position).getReview_content());
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
