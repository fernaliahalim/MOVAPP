package com.example.fernalia.movapp;

/**
 * Created by Ferna on 29/11/2016.
 */

public class DataObject {
    private String id;
    private String drawable;
    private String title;
    private String rated;
    private String release_date;
    private String overview;
    private String backdrop_path;
    private String popularity;

    //trailer movie data
    private String trailer_key;
    private String trailer_name;

    //review movie data
    private String review_author;
    private String review_content;
    private String review_url;

    public DataObject(){

    }

    public DataObject(String id, String drawable, String title, String popularity, String release_date, String overview, String backdrop_path, String rated){
        this.id = id;
        this.drawable = drawable;
        this.title = title;
        this.popularity = popularity;
        this.release_date = release_date;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
        this.rated = rated;
    }

    public DataObject(String trailer_key, String trailer_name){
        this.trailer_key  = trailer_key;
        this.trailer_name = trailer_name;
    }

    public DataObject(String review_author, String review_content, String review_url){
        this.review_author  = review_author;
        this.review_content = review_content;
        this.review_url     = review_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrawable() {
        return drawable;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getTrailer_key() {
        return trailer_key;
    }

    public void setTrailer_key(String trailer_key) {
        this.trailer_key = trailer_key;
    }

    public String getTrailer_name() {
        return trailer_name;
    }

    public void setTrailer_name(String trailer_name) {
        this.trailer_name = trailer_name;
    }

    public String getReview_author() {
        return review_author;
    }

    public void setReview_author(String review_author) {
        this.review_author = review_author;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public String getReview_url() {
        return review_url;
    }

    public void setReview_url(String review_url) {
        this.review_url = review_url;
    }
}