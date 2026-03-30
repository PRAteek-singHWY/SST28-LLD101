package com.example.moviebooking;

import com.example.moviebooking.models.Seat;
import com.example.moviebooking.enums.SeatType;
import com.example.moviebooking.services.SeatBook;
import com.example.moviebooking.services.Transaction;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Seat s1 = new Seat(1, SeatType.NORMAL, 150.0);
        Seat s2 = new Seat(2, SeatType.NORMAL, 150.0);
        Seat s3 = new Seat(3, SeatType.PREMIUM, 250.0);

        Transaction transaction = new Transaction();
        SeatBook seatBook = new SeatBook(transaction, "PVR Cinemas", "PREMIUM");
        
        Thread user1 = new Thread(() -> {
            boolean success = seatBook.finalBooking(Arrays.asList(s1, s2));
            System.out.println("User 1 booking success: " + success);
        });

        Thread user2 = new Thread(() -> {
            boolean success = seatBook.finalBooking(Arrays.asList(s2, s3));
            System.out.println("User 2 booking success: " + success);
        });

        user1.start();
        user2.start();
    }
}
