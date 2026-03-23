package com.example.parking.services;

import com.example.parking.enums.SlotType;
import com.example.parking.models.ParkingSlot;
import com.example.parking.models.ParkingTicket;
import com.example.parking.models.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ParkingLotSystem {
    private List<ParkingSlot> slots;

    public ParkingLotSystem(List<ParkingSlot> slots) {
        this.slots = slots;
    }

    /**
     * Finds the nearest compatible slot based on the entry gate.
     * Note: If requestedSlotType is specified as a preference, we prioritize finding that type,
     * otherwise we fall back to any compatible slot.
     */
    public ParkingTicket park(Vehicle vehicleDetails, LocalDateTime entryTime, SlotType requestedSlotType, String entryGateId) {
        ParkingSlot bestSlot = null;
        int minDistance = Integer.MAX_VALUE;

        // Hunt for the optimum distance slot mapping configuration
        for (ParkingSlot slot : slots) {
            if (slot.isAvailable() && slot.isCompatible(vehicleDetails.getType(), requestedSlotType)) {
                // If they specifically requested an upgrade size, we enforce the match if possible.
                // If no specific upgrade demanded, anything compatible closest wins.
                if (requestedSlotType != null && slot.getSlotType() != requestedSlotType) continue;

                int dist = slot.getDistanceFromGate(entryGateId);
                if (dist < minDistance) {
                    minDistance = dist;
                    bestSlot = slot;
                }
            }
        }
        
        // If the exact requested slot type was null or unavailable, try to find any compatible upgradable slot instead (fallback).
        if (bestSlot == null && requestedSlotType != null) {
            for (ParkingSlot slot : slots) {
                if (slot.isAvailable() && slot.isCompatible(vehicleDetails.getType(), null)) {
                    int dist = slot.getDistanceFromGate(entryGateId);
                    if (dist < minDistance) {
                        minDistance = dist;
                        bestSlot = slot;
                    }
                }
            }
        }

        if (bestSlot == null) {
            throw new RuntimeException("No compatible slots available for " + vehicleDetails.getType());
        }

        // Lock the slot
        bestSlot.setAvailable(false);

        // Generate Ticket
        String ticketId = UUID.randomUUID().toString().substring(0, 8); // simple short ID
        ParkingTicket ticket = new ParkingTicket(ticketId, vehicleDetails, bestSlot, entryTime);

        System.out.println("✅ Vehicle " + vehicleDetails.getLicensePlate() + " parked at " + bestSlot.getSlotId() +
                " (Type: " + bestSlot.getSlotType() + "). Entry Gate: " + entryGateId);

        return ticket;
    }

    public void status() {
        int smallCount = 0, mediumCount = 0, largeCount = 0;
        for (ParkingSlot slot : slots) {
            if (slot.isAvailable()) {
                if (slot.getSlotType() == SlotType.SMALL) smallCount++;
                else if (slot.getSlotType() == SlotType.MEDIUM) mediumCount++;
                else if (slot.getSlotType() == SlotType.LARGE) largeCount++;
            }
        }
        System.out.println("📊 Parking Lot Status (Available Slots):");
        System.out.println("   SMALL: " + smallCount);
        System.out.println("   MEDIUM: " + mediumCount);
        System.out.println("   LARGE: " + largeCount);
    }

    public double exit(ParkingTicket ticket, LocalDateTime exitTime) {
        ParkingSlot allocatedSlot = ticket.getAllocatedSlot();

        // Calculate hours parked perfectly simulating duration. Partial hours are rounded UP. 
        long minutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
        double hoursParked = Math.ceil(minutes / 60.0);
        
        if (hoursParked <= 0) hoursParked = 1; // Minimum 1 hour charge if left immediately

        double billAmount = hoursParked * allocatedSlot.getSlotType().getHourlyRate();

        // Release the slot
        allocatedSlot.setAvailable(true);

        System.out.println("💳 Vehicle " + ticket.getVehicle().getLicensePlate() + " exited.");
        System.out.println("   Slot freed: " + allocatedSlot.getSlotId());
        System.out.println("   Duration: " + hoursParked + " hours");
        System.out.println("   Bill Total: $" + billAmount + " (Rate applied based on " + allocatedSlot.getSlotType() + ")");

        return billAmount;
    }
}
