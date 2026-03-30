package com.example.elevator.models;

import com.example.elevator.enums.Direction;
import java.util.ArrayList;
import java.util.List;

public class Floor {
    private int floorNumber;
    private Button upButton;
    private Button downButton;
    private List<Display> elevatorDisplays;

    public Floor(int floorNumber, boolean isTopFloor, boolean isBottomFloor, int numElevators) {
        this.floorNumber = floorNumber;
        this.elevatorDisplays = new ArrayList<>();
        
        for (int i = 0; i < numElevators; i++) {
            this.elevatorDisplays.add(new Display());
        }
        
        if (!isTopFloor) {
            this.upButton = new Button(floorNumber, Direction.UP);
        }
        if (!isBottomFloor) {
            this.downButton = new Button(floorNumber, Direction.DOWN);
        }
    }

    public void pressUpButton() {
        if (upButton != null) upButton.press();
    }

    public void pressDownButton() {
        if (downButton != null) downButton.press();
    }

    public List<Display> getElevatorDisplays() {
        return elevatorDisplays;
    }
    
    public int getFloorNumber() {
        return floorNumber;
    }

    public Button getUpButton() {
        return upButton;
    }

    public Button getDownButton() {
        return downButton;
    }
}
