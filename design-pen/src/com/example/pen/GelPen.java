package com.example.pen;

public class GelPen extends Pen implements Refillable {
    private double inkLevel;

    public GelPen(Color color) {
        super(color, PenType.GEL_PEN);
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
            System.out.println("The pen is empty.");
            return;
        }

        System.out.println("Writing smoothly in " + color + " with Gel Pen: " + content);
        inkLevel -= 5.0; // Consumes more ink

        if (inkLevel <= 0) {
            inkLevel = 0;
            state = PenState.EMPTY;
        }
    }

    @Override
    public void refill(Color newColor) {
        this.color = newColor; // Answers: "Can the color of pen be changed?" -> Yes!
        this.inkLevel = 100.0;

        System.out.println("Gel Pen refilled with " + color + " ink. Ink: " + inkLevel + "%");
        state = PenState.CLOSED;
    }
}
