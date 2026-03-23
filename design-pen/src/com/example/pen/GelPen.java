package com.example.pen;

public class GelPen extends Pen implements Refillable {
    private double inkLevel;

    public GelPen(String brand) {
        super(brand, PenType.GEL_PEN);
        this.inkLevel = 100.0;
    }

    @Override
    public void write(String content) {
        if (state != PenState.OPEN) {
            System.out.println("Cannot write. Please start() the pen first.");
            return;
        }

        if (inkLevel <= 0) {
            state = PenState.EMPTY;
            System.out.println("Cannot write. The pen is empty.");
            return;
        }

        System.out.println("Writing smoothly with Gel Pen: " + content);
        inkLevel -= 5.0;

        if (inkLevel <= 0) {
            inkLevel = 0;
            state = PenState.EMPTY;
        }
    }

    @Override
    public void refill(boolean full) {
        this.inkLevel = full ? 100.0 : this.inkLevel + 50.0;
        if (this.inkLevel > 100.0) this.inkLevel = 100.0;

        System.out.println(brand + " Gel Pen refilled. Ink: " + inkLevel + "%");
        state = PenState.CLOSED;
    }
}
