package com.example.elevator;

import com.example.elevator.core.ElevatorSystem;
import com.example.elevator.models.ElevatorCar;
import com.example.elevator.enums.Direction;

public class App {
    public static void main(String[] args) {
        ElevatorSystem system = ElevatorSystem.getInstance();
        system.initialize(3, 15);

        system.requestElevator(5, Direction.UP);
        
        ElevatorCar car1 = system.getElevators().get(0);
        car1.addPassenger(70);
        car1.pressButton(10);
        car1.move(Direction.UP);
        
        car1.setCurrentFloor(5);
        car1.stop();
        car1.openDoor();
        car1.addPassenger(80);
        car1.closeDoor();
        
        car1.move(Direction.UP);
        car1.setCurrentFloor(10);
        car1.stop();
        car1.openDoor();
        car1.removePassenger(70);
        car1.removePassenger(80);
        car1.closeDoor();
    }
}
