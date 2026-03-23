package com.example.pen;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Testing Ball Pen ===");
        Pen reynolds = new BallPen("Reynolds");
        reynolds.write("Hello World"); // Should fail, not open
        reynolds.start();              // Opens the pen
        reynolds.write("Hello World"); // Should succeed
        reynolds.close();
        
        System.out.println("\n=== Testing Gel Pen ===");
        Pen rorito = new GelPen("Rorito");
        rorito.start();
        for (int i = 0; i < 21; i++) { // GelPen reduces 5.0 per write, so 100 / 5 = 20 writes before empty
            rorito.write("Line " + (i + 1));
        }
        
        // At this point rorito should be EMPTY
        rorito.write("One more line");
        
        if (rorito instanceof Refillable) {
            ((Refillable) rorito).refill(true); // Refill completely
        }
        
        rorito.start(); // Open again
        rorito.write("Back in business!");
        rorito.close();
        
        System.out.println("\n=== Testing Fountain Pen ===");
        Pen parker = new FountainPen("Parker");
        parker.start();
        parker.write("Signing a very important document.");
        parker.close();
    }
}
