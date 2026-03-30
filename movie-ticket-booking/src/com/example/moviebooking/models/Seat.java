package com.example.moviebooking.models;

import com.example.moviebooking.enums.SeatStatus;
import com.example.moviebooking.enums.SeatType;

public class Seat {
    private int seatId;
    private SeatType seatType;
    private SeatStatus status;
    private double price;

    public Seat(int seatId, SeatType seatType, double price) {
        this.seatId = seatId;
        this.seatType = seatType;
        this.status = SeatStatus.SEAT_NOT_BOOKED;
        this.price = price;
    }

    public int getSeatId() { return seatId; }
    public SeatType getSeatType() { return seatType; }
    public SeatStatus getStatus() { return status; }
    public double getPrice() { return price; }
    
    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
