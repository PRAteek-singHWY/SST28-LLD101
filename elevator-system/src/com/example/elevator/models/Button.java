package com.example.elevator.models;

import com.example.elevator.enums.Direction;

public class Button {
    private int floorNumber;
    private Direction direction;
    private boolean isPressed;

    public Button(int floorNumber, Direction direction) {
        this.floorNumber = floorNumber;
        this.direction = direction;
        this.isPressed = false;
    }

    public void press() {
        if (!isPressed) {
            this.isPressed = true;
            System.out.println("Button Pressed: Floor " + floorNumber + (direction != null ? ", " + direction : ""));
        }
    }

    public void reset() {
        this.isPressed = false;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Direction getDirection() {
        return direction;
    }
}
