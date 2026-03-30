package com.example.moviebooking.models;

import com.example.moviebooking.enums.MovieStatus;
import com.example.moviebooking.enums.MovieType;

public class Movie {
    private int movieId;
    private int theaterId;
    private MovieType movieType;
    private MovieStatus movieStatus;

    public Movie(int movieId, int theaterId, MovieType movieType, MovieStatus movieStatus) {
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.movieType = movieType;
        this.movieStatus = movieStatus;
    }

    public int getMovieId() { return movieId; }
    public int getTheaterId() { return theaterId; }
    public MovieType getMovieType() { return movieType; }
    public MovieStatus getMovieStatus() { return movieStatus; }
}
