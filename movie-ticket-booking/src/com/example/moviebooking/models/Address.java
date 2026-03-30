package com.example.moviebooking.models;

public class Address {
    private String city;
    private String pinCode;
    private String state;
    private String streetNo;
    private String landmark;

    public Address(String city, String pinCode, String state, String streetNo, String landmark) {
        this.city = city;
        this.pinCode = pinCode;
        this.state = state;
        this.streetNo = streetNo;
        this.landmark = landmark;
    }

    public String getCity() { return city; }
    public String getPinCode() { return pinCode; }
    public String getState() { return state; }
    public String getStreetNo() { return streetNo; }
    public String getLandmark() { return landmark; }
}
