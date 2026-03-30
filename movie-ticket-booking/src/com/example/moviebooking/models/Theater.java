package com.example.moviebooking.models;

import java.util.List;

public class Theater {
    private int theaterId;
    private String theaterName;
    private Address address;
    private List<Movie> movies;
    private float rating;

    public Theater(int theaterId, String theaterName, Address address, List<Movie> movies, float rating) {
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        this.address = address;
        this.movies = movies;
        this.rating = rating;
    }

    public int getTheaterId() { return theaterId; }
    public String getTheaterName() { return theaterName; }
    public Address getAddress() { return address; }
    public List<Movie> getMovies() { return movies; }
    public float getRating() { return rating; }
}
