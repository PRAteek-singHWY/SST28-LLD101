package com.example.elevator.states;

import com.example.elevator.models.ElevatorCar;
import com.example.elevator.enums.Direction;

public interface State {
    void handleRequest(ElevatorCar elevatorCar);
    void move(ElevatorCar elevatorCar, Direction direction);
    void stop(ElevatorCar elevatorCar);
    void openDoor(ElevatorCar elevatorCar);
    void closeDoor(ElevatorCar elevatorCar);
}
