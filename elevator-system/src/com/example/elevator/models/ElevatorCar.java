package com.example.elevator.models;

import com.example.elevator.enums.Direction;
import com.example.elevator.enums.DoorState;
import com.example.elevator.enums.ElevatorState;
import com.example.elevator.states.State;
import com.example.elevator.states.IdleState;
import com.example.elevator.states.MovingState;
import java.util.ArrayList;
import java.util.List;

public class ElevatorCar {
    private int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState elevatorStateEnum;
    private DoorState doorStateEnum;
    private int currentCapacityPpl;
    private double currentCapacityKg;
    private final int MAX_CAPACITY_PPL = 8;
    private final double MAX_CAPACITY_KG = 680.0;
    
    private State idleState;
    private State movingState;
    private State currentState;
    
    private Display internalDisplay;
    private List<Button> internalButtons;

    public ElevatorCar(int id, int numFloors) {
        this.id = id;
        this.currentFloor = 1;
        this.direction = Direction.IDLE;
        this.elevatorStateEnum = ElevatorState.IDLE;
        this.doorStateEnum = DoorState.CLOSED;
        this.currentCapacityPpl = 0;
        this.currentCapacityKg = 0.0;
        
        this.idleState = new IdleState();
        this.movingState = new MovingState();
        this.currentState = idleState;
        
        this.internalDisplay = new Display();
        this.internalButtons = new ArrayList<>();
        
        for (int i = 1; i <= numFloors; i++) {
            internalButtons.add(new Button(i, null));
        }
    }

    public void move(Direction dir) {
        currentState.move(this, dir);
    }

    public void stop() {
        currentState.stop(this);
    }

    public void openDoor() {
        currentState.openDoor(this);
    }

    public void closeDoor() {
        currentState.closeDoor(this);
    }
    
    public void addPassenger(int weight) {
        if (currentCapacityPpl < MAX_CAPACITY_PPL && (currentCapacityKg + weight) <= MAX_CAPACITY_KG) {
            currentCapacityPpl++;
            currentCapacityKg += weight;
            updateDisplay();
        } else {
            System.out.println("Elevator " + id + " is full");
        }
    }
    
    public void removePassenger(int weight) {
        if (currentCapacityPpl > 0) {
            currentCapacityPpl--;
            currentCapacityKg -= weight;
            updateDisplay();
        }
    }
    
    public void pressButton(int floor) {
        if (floor >= 1 && floor <= internalButtons.size()) {
            internalButtons.get(floor - 1).press();
        }
    }
    
    private void updateDisplay() {
        internalDisplay.setDisplay(currentFloor, direction, currentCapacityPpl);
    }

    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    
    public void setCurrentFloor(int currentFloor) { 
        this.currentFloor = currentFloor; 
        updateDisplay(); 
    }
    
    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; updateDisplay(); }
    public ElevatorState getElevatorStateEnum() { return elevatorStateEnum; }
    public void setElevatorStateEnum(ElevatorState elevatorStateEnum) { this.elevatorStateEnum = elevatorStateEnum; }
    public DoorState getDoorStateEnum() { return doorStateEnum; }
    public void setDoorStateEnum(DoorState doorStateEnum) { this.doorStateEnum = doorStateEnum; }
    
    public State getIdleState() { return idleState; }
    public State getMovingState() { return movingState; }
    public void setCurrentState(State state) { this.currentState = state; }
    public Display getInternalDisplay() { return internalDisplay; }
}
