package com.example.elevator.dispatch;

import com.example.elevator.models.ElevatorCar;
import com.example.elevator.enums.Direction;
import java.util.List;

public interface DispatchStrategy {
    ElevatorCar selectElevator(List<ElevatorCar> elevators, int targetFloor, Direction direction);
}
