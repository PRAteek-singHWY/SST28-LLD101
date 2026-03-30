package com.example.moviebooking.services;

import com.example.moviebooking.models.Seat;
import com.example.moviebooking.enums.PaymentStatus;
import java.util.List;

public class Transaction {
    public PaymentStatus pay(String ticketType, List<Seat> seatsBooked, String place) {
        System.out.println("Processing payment for " + seatsBooked.size() + " " + ticketType + " tickets at " + place);
        return PaymentStatus.PAID;
    }
}
