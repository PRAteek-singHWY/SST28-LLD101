package com.example.pen;

public abstract class Pen {
    protected String brand;
    protected PenType type;
    protected PenState state;

    public Pen(String brand, PenType type) {
        this.brand = brand;
        this.type = type;
        this.state = PenState.NEW;
    }

    public void start() {
        if (state == PenState.OPEN) {
            System.out.println(brand + " pen is already open.");
        } else if (state == PenState.EMPTY) {
            System.out.println(brand + " pen is empty. Please refill first.");
        } else {
            this.state = PenState.OPEN;
            System.out.println(brand + " pen is now open.");
        }
    }

    public void close() {
        if (state == PenState.CLOSED) {
            System.out.println(brand + " pen is already closed.");
        } else {
            this.state = PenState.CLOSED;
            System.out.println(brand + " pen is now closed.");
        }
    }

    public abstract void write(String content);
}
