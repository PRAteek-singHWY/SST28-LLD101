package com.example.pen;

public class FountainPen extends Pen implements Refillable {
    private double inkLevel;

    public FountainPen(Color color) {
        super(color, PenType.FOUNTAIN_PEN);
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

        System.out.println("Writing elegantly in " + color + " with Fountain Pen: " + content);
        inkLevel -= 10.0; // Consumes the most ink

        if (inkLevel <= 0) {
            inkLevel = 0;
            state = PenState.EMPTY;
        }
    }

    @Override
    public void refill(Color newColor) {
        this.color = newColor; // Answers: "Can the color of pen be changed?" -> Yes!
        this.inkLevel = 100.0;

        System.out.println("Fountain Pen refilled with " + color + " premium ink. Ink: " + inkLevel + "%");
        state = PenState.CLOSED;
    }
}
