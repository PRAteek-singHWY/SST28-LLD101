package com.example.elevator.states;

import com.example.elevator.models.ElevatorCar;
import com.example.elevator.enums.Direction;
import com.example.elevator.enums.ElevatorState;

public class MovingState implements State {
    @Override
    public void handleRequest(ElevatorCar elevatorCar) {
    }

    @Override
    public void move(ElevatorCar elevatorCar, Direction direction) {
    }

    @Override
    public void stop(ElevatorCar elevatorCar) {
        elevatorCar.setDirection(Direction.IDLE);
        elevatorCar.setCurrentState(elevatorCar.getIdleState());
        elevatorCar.setElevatorStateEnum(ElevatorState.IDLE);
        System.out.println("Elevator " + elevatorCar.getId() + " stopped");
    }

    @Override
    public void openDoor(ElevatorCar elevatorCar) {
        System.out.println("Cannot open doors while moving");
    }

    @Override
    public void closeDoor(ElevatorCar elevatorCar) {
        System.out.println("Doors are already closed");
    }
}
