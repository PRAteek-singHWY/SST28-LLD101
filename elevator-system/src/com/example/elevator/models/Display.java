package com.example.elevator.models;

import com.example.elevator.enums.Direction;

public class Display {
    private int currentFloor;
    private Direction currentDirection;
    private Integer currentCapacity; 

    public Display() {
        this.currentFloor = 1;
        this.currentDirection = Direction.IDLE;
        this.currentCapacity = 0;
    }

    public void setDisplay(int floor, Direction direction, Integer capacity) {
        this.currentFloor = floor;
        this.currentDirection = direction;
        this.currentCapacity = capacity;
    }

    public void showInternal() {
        System.out.println("Internal Display: Floor " + currentFloor + " | Direction " + currentDirection + " | Capacity " + currentCapacity);
    }

    public void showExternal() {
        System.out.println("External Display: Floor " + currentFloor + " | Direction " + currentDirection);
    }
}
