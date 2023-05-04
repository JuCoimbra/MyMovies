package com.example.mymovies;

public class UserMovies {

    private String movieID;
    private String userID;

    public UserMovies(String movieID, String userID) {
        this.movieID = movieID;
        this.userID = userID;
    }

    public String getMovie() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
