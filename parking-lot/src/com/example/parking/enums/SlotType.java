package com.example.parking.enums;

public enum SlotType {
    SMALL(10.0),   // $10/hour
    MEDIUM(20.0),  // $20/hour
    LARGE(30.0);   // $30/hour

    private final double hourlyRate;

    SlotType(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}
