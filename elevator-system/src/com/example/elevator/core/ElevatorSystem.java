package com.example.elevator.core;

import com.example.elevator.models.ElevatorCar;
import com.example.elevator.models.Floor;
import com.example.elevator.enums.Direction;
import com.example.elevator.dispatch.SmartDispatcher;
import com.example.elevator.dispatch.LOOKDispatchStrategy;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private static ElevatorSystem instance;
    private List<ElevatorCar> elevators;
    private List<Floor> floors;
    private SmartDispatcher dispatcher;

    private ElevatorSystem() {
        elevators = new ArrayList<>();
        floors = new ArrayList<>();
        dispatcher = new SmartDispatcher(new LOOKDispatchStrategy());
    }

    public static synchronized ElevatorSystem getInstance() {
        if (instance == null) {
            instance = new ElevatorSystem();
        }
        return instance;
    }

    public void initialize(int numElevators, int numFloors) {
        for (int i = 1; i <= numElevators; i++) {
            elevators.add(new ElevatorCar(i, numFloors));
        }
        
        for (int i = 1; i <= numFloors; i++) {
            boolean isTop = (i == numFloors);
            boolean isBottom = (i == 1);
            floors.add(new Floor(i, isTop, isBottom, numElevators));
        }
    }

    public void requestElevator(int floorNumber, Direction direction) {
        if (floorNumber >= 1 && floorNumber <= floors.size()) {
            Floor floor = floors.get(floorNumber - 1);
            if (direction == Direction.UP) {
                floor.pressUpButton();
            } else if (direction == Direction.DOWN) {
                floor.pressDownButton();
            }
            
            ElevatorCar optimalElevator = dispatcher.dispatch(elevators, floorNumber, direction);
            
            if (optimalElevator != null) {
                System.out.println("Elevator " + optimalElevator.getId() + " dispatched to Floor " + floorNumber);
            } else {
                System.out.println("No elevator available at the moment");
            }
        }
    }

    public List<ElevatorCar> getElevators() {
        return elevators;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
