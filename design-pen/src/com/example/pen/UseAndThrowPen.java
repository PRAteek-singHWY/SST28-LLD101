package com.example.pen;

public class UseAndThrowPen extends Pen {
    private double inkLevel;

    public UseAndThrowPen(Color color) {
        super(color, PenType.BALL_PEN); // Typically a ball pen mechanism
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
            System.out.println("The Use & Throw pen is empty and cannot be refilled.");
            return;
        }

        System.out.println("Writing with Use & Throw Pen in " + color + ": " + content);
        inkLevel -= 5.0;
        
        if (inkLevel <= 0) {
            inkLevel = 0;
            state = PenState.EMPTY;
        }
    }
    // Notice there is no refill() method implemented!
}
