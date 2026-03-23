package com.example.pen;

public abstract class Pen {
    protected Color color;
    protected PenType type;
    protected PenState state;

    public Pen(Color color, PenType type) {
        this.color = color;
        this.type = type;
        this.state = PenState.NEW;
    }

    public void start() {
        if (state == PenState.OPEN) {
            System.out.println("The " + color + " pen is already open.");
        } else if (state == PenState.EMPTY) {
            System.out.println("The " + color + " pen is empty. Please refill first.");
        } else {
            this.state = PenState.OPEN;
            System.out.println("The " + color + " pen is now ready (cap removed or clicked).");
        }
    }

    public void close() {
        if (state == PenState.CLOSED) {
            System.out.println("The " + color + " pen is already closed.");
        } else {
            this.state = PenState.CLOSED;
            System.out.println("The " + color + " pen is nicely closed (capped or clicked back).");
        }
    }

    public abstract void write(String content);
}
