package com.example.elevator.dispatch;

import com.example.elevator.models.ElevatorCar;
import com.example.elevator.enums.Direction;
import com.example.elevator.enums.ElevatorState;
import java.util.List;

public class LOOKDispatchStrategy implements DispatchStrategy {
    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, int targetFloor, Direction direction) {
        ElevatorCar bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorCar elevator : elevators) {
            if (elevator.getElevatorStateEnum() == ElevatorState.MAINTENANCE) {
                continue;
            }

            int distance = Math.abs(elevator.getCurrentFloor() - targetFloor);
            
            if (elevator.getElevatorStateEnum() == ElevatorState.IDLE) {
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            } else if (elevator.getDirection() == direction) {
                if (direction == Direction.UP && elevator.getCurrentFloor() <= targetFloor) {
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = elevator;
                    }
                } else if (direction == Direction.DOWN && elevator.getCurrentFloor() >= targetFloor) {
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = elevator;
                    }
                }
            }
        }

        if (bestElevator == null) {
            for (ElevatorCar elevator : elevators) {
                if (elevator.getElevatorStateEnum() != ElevatorState.MAINTENANCE) {
                    int distance = Math.abs(elevator.getCurrentFloor() - targetFloor);
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = elevator;
                    }
                }
            }
        }

        return bestElevator;
    }
}
