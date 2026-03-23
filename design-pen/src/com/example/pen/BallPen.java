package com.example.pen;

public class BallPen extends Pen implements Refillable {
    private double inkLevel;

    public BallPen(Color color) {
        super(color, PenType.BALL_PEN);
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

        System.out.println("Writing gracefully in " + color + ": " + content);
        inkLevel -= 2.0; // Consumes less ink
        
        if (inkLevel <= 0) {
            inkLevel = 0;
            state = PenState.EMPTY;
        }
    }

    @Override
    public void refill(Color newColor) {
        this.color = newColor; // Answers: "Can the color of pen be changed?" -> Yes!
        this.inkLevel = 100.0;
        
        System.out.println("Ball Pen refilled with " + color + " ink. Ink: " + inkLevel + "%");
        state = PenState.CLOSED;
    }
}
