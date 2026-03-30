package com.example.moviebooking.models;

import com.example.moviebooking.enums.PaymentStatus;
import java.util.Date;
import java.util.List;
import java.time.LocalTime;

public class Booking {
    private int bookingId;
    private int userId;
    private int movieId;
    private List<Seat> bookedSeats;
    private int amount;
    private PaymentStatus status_of_payment;
    private Date booked_date;
    private LocalTime movie_timing;

    public Booking(int bookingId, int userId, int movieId, List<Seat> bookedSeats, 
                   int amount, PaymentStatus status_of_payment, Date booked_date, LocalTime movie_timing) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.movieId = movieId;
        this.bookedSeats = bookedSeats;
        this.amount = amount;
        this.status_of_payment = status_of_payment;
        this.booked_date = booked_date;
        this.movie_timing = movie_timing;
    }

    public int getBookingId() { return bookingId; }
    public int getUserId() { return userId; }
    public int getMovieId() { return movieId; }
    public List<Seat> getBookedSeats() { return bookedSeats; }
    public int getAmount() { return amount; }
    public PaymentStatus getStatusOfPayment() { return status_of_payment; }
    public Date getBookedDate() { return booked_date; }
    public LocalTime getMovieTiming() { return movie_timing; }
}
