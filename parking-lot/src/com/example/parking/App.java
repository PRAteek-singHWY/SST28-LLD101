package com.example.parking;

import com.example.parking.enums.SlotType;
import com.example.parking.enums.VehicleType;
import com.example.parking.models.ParkingSlot;
import com.example.parking.models.ParkingTicket;
import com.example.parking.models.Vehicle;
import com.example.parking.services.ParkingLotSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Initializing Multilevel Parking Lot ===");
        
        List<ParkingSlot> slots = new ArrayList<>();
        
        // Let's create slots with relative distances from 2 hypothetical gates: "GateA" and "GateB"
        
        // 2 SMALL slots
        slots.add(new ParkingSlot("S-101", SlotType.SMALL, Map.of("GateA", 5, "GateB", 20)));
        slots.add(new ParkingSlot("S-102", SlotType.SMALL, Map.of("GateA", 20, "GateB", 5))); // Closer to Gate B

        // 2 MEDIUM slots
        slots.add(new ParkingSlot("M-201", SlotType.MEDIUM, Map.of("GateA", 10, "GateB", 15)));
        slots.add(new ParkingSlot("M-202", SlotType.MEDIUM, Map.of("GateA", 15, "GateB", 10))); // Closer to Gate B

        // 1 LARGE slot
        slots.add(new ParkingSlot("L-301", SlotType.LARGE, Map.of("GateA", 30, "GateB", 30)));

        ParkingLotSystem system = new ParkingLotSystem(slots);
        system.status();

        System.out.println("\n=== Scenario 1: Bike entering GateB ===");
        Vehicle bike1 = new Vehicle("DL-BIKE-1", VehicleType.TWO_WHEELER);
        ParkingTicket ticket1 = system.park(bike1, LocalDateTime.now(), null, "GateB");
        // Should logically grab S-102 because its distance is shorter.

        System.out.println("\n=== Scenario 2: Car entering GateA ===");
        Vehicle car1 = new Vehicle("MH-CAR-9", VehicleType.CAR);
        ParkingTicket ticket2 = system.park(car1, LocalDateTime.now().minusHours(2), null, "GateA");
        // Should grab M-201 being closest to GateA.

        system.status();

        System.out.println("\n=== Scenario 3: Another Bike forced to Upgrade Slot ===");
        Vehicle bike2 = new Vehicle("KA-BIKE-5", VehicleType.TWO_WHEELER);
        ParkingTicket ticket3 = system.park(bike2, LocalDateTime.now(), null, "GateA"); // Claims S-101
        
        // All SMALL slots are now full (S-101 and S-102).
        system.status();
        
        Vehicle bike3 = new Vehicle("AP-BIKE-8", VehicleType.TWO_WHEELER);
        // Will park in Medium or Large because Small is exhausted. Will map to M-202 natively!
        ParkingTicket ticket4 = system.park(bike3, LocalDateTime.now(), null, "GateB");

        System.out.println("\n=== Scenario 4: Exit & Billing ===");
        // Simulate exiting Car after exactly 0 hours of wait (but ticket mock generated minus 2 hours!)
        system.exit(ticket2, LocalDateTime.now()); 
        
        // Exit Bike3 which was parked in a MEDIUM slot! (Expect billing to be MEDIUM rate applied, e.g. 20 * hours)
        System.out.println("\nExiting Bike that mapped to Medium Slot:");
        system.exit(ticket4, LocalDateTime.now().plusHours(3).plusMinutes(15)); // Parked for 3 hours 15 mins -> Round to 4 hours. Expect: 4 * 20 = $80

        System.out.println("\n=== Final Status ===");
        system.status();
    }
}
