package com.example.elevator.states;

import com.example.elevator.models.ElevatorCar;
import com.example.elevator.enums.Direction;
import com.example.elevator.enums.DoorState;
import com.example.elevator.enums.ElevatorState;

public class IdleState implements State {
    @Override
    public void handleRequest(ElevatorCar elevatorCar) {
    }

    @Override
    public void move(ElevatorCar elevatorCar, Direction direction) {
        elevatorCar.setDirection(direction);
        elevatorCar.setCurrentState(elevatorCar.getMovingState());
        elevatorCar.setElevatorStateEnum(ElevatorState.MOVING);
        System.out.println("Elevator " + elevatorCar.getId() + " is moving " + direction);
    }

    @Override
    public void stop(ElevatorCar elevatorCar) {
    }

    @Override
    public void openDoor(ElevatorCar elevatorCar) {
        elevatorCar.setDoorStateEnum(DoorState.OPEN);
        System.out.println("Elevator " + elevatorCar.getId() + " doors OPENING");
    }

    @Override
    public void closeDoor(ElevatorCar elevatorCar) {
        elevatorCar.setDoorStateEnum(DoorState.CLOSED);
        System.out.println("Elevator " + elevatorCar.getId() + " doors CLOSING");
    }
}
