package com.example.movierating_madcw.Model;

public class MovieModel {

    String movie_id;
    String movie_name;
    String movie_year;
    String movie_director;
    String movie_actor_actress;
    String movie_rating;
    String movie_review;
    boolean movie_favorite;

    public boolean getMovie_favorite() {
        return movie_favorite;
    }

    public void setMovie_favorite(boolean movie_favorite) {
        this.movie_favorite = movie_favorite;
    }

    public MovieModel(String movie_id, String movie_name, String movie_year, String movie_director, String movie_actor_actress, String movie_rating, String movie_review, boolean movie_favorite) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_year = movie_year;
        this.movie_director = movie_director;
        this.movie_actor_actress = movie_actor_actress;
        this.movie_rating = movie_rating;
        this.movie_review = movie_review;
        this.movie_favorite=movie_favorite;

    }

    public MovieModel() {
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_year() {
        return movie_year;
    }

    public void setMovie_year(String movie_year) {
        this.movie_year = movie_year;
    }

    public String getMovie_director() {
        return movie_director;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }

    public String getMovie_actor_actress() {
        return movie_actor_actress;
    }

    public void setMovie_actor_actress(String movie_actor_actress) {
        this.movie_actor_actress = movie_actor_actress;
    }

    public String getMovie_rating() {
        return movie_rating;
    }

    public void setMovie_rating(String movie_rating) {
        this.movie_rating = movie_rating;
    }

    public String getMovie_review() {
        return movie_review;
    }

    public void setMovie_review(String movie_review) {
        this.movie_review = movie_review;
    }
}
