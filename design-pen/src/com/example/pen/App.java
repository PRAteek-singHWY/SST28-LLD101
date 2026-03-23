package com.example.pen;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Testing Ball Pen ===");
        Pen bluePen = new BallPen(Color.BLUE);
        bluePen.write("Hello World"); // Should fail, not open
        bluePen.start();              // Opens the pen (cap removed or clicked)
        bluePen.write("Hello World"); // Should succeed
        bluePen.close();              // Closes pen
        
        System.out.println("\n=== Testing Gel Pen & Color Change ===");
        Pen redGelPen = new GelPen(Color.RED);
        redGelPen.start();
        for (int i = 0; i < 21; i++) { // GelPen reduces 5.0 per write
            redGelPen.write("Line " + (i + 1));
        }
        
        // At this point it should be EMPTY
        redGelPen.write("One more line");
        
        // Refill and change color!
        if (redGelPen instanceof Refillable) {
            ((Refillable) redGelPen).refill(Color.BLACK); 
        }
        
        redGelPen.start(); 
        redGelPen.write("Back in business with new color!");
        redGelPen.close();
        
        System.out.println("\n=== Testing Use & Throw Pen ===");
        Pen disposable = new UseAndThrowPen(Color.GREEN);
        disposable.start();
        disposable.write("Using a disposable pen.");
        disposable.close();
        // disposable.refill() does not exist!
    }
}
