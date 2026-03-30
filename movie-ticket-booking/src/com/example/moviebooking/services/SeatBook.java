package com.example.moviebooking.services;

import com.example.moviebooking.models.Seat;
import com.example.moviebooking.enums.SeatStatus;
import java.util.List;

public class SeatBook {
    private Transaction transactionObj;
    private String place;
    private String ticketType;

    public SeatBook(Transaction transactionObj, String place, String ticketType) {
        this.transactionObj = transactionObj;
        this.place = place;
        this.ticketType = ticketType;
    }

    public synchronized boolean finalBooking(List<Seat> selectedSeats) {
        if (!checkAvailability(selectedSeats)) {
            System.out.println("One or more selected seats are already booked.");
            return false;
        }

        for (Seat seat : selectedSeats) {
            seat.setStatus(SeatStatus.SEAT_BOOKED);
        }

        transactionObj.pay(ticketType, selectedSeats, place);
        System.out.println("Booking successful for " + selectedSeats.size() + " seats.");
        return true;
    }

    private boolean checkAvailability(List<Seat> selectedSeats) {
        for (Seat seat : selectedSeats) {
            if (seat.getStatus() == SeatStatus.SEAT_BOOKED) {
                return false;
            }
        }
        return true;
    }
}
