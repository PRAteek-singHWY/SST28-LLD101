package com.example.elevator.dispatch;

import com.example.elevator.models.ElevatorCar;
import com.example.elevator.enums.Direction;
import java.util.List;

public class SmartDispatcher {
    private DispatchStrategy dispatchStrategy;

    public SmartDispatcher(DispatchStrategy dispatchStrategy) {
        this.dispatchStrategy = dispatchStrategy;
    }

    public void setDispatchStrategy(DispatchStrategy dispatchStrategy) {
        this.dispatchStrategy = dispatchStrategy;
    }

    public ElevatorCar dispatch(List<ElevatorCar> elevators, int targetFloor, Direction direction) {
        return dispatchStrategy.selectElevator(elevators, targetFloor, direction);
    }
}
