package com.example.parking.models;

import com.example.parking.enums.SlotType;
import com.example.parking.enums.VehicleType;

import java.util.Map;

public class ParkingSlot {
    private String slotId;
    private SlotType slotType;
    private boolean isAvailable;
    // Map defining the distance of this slot from each physical gate, e.g. Gate1 -> 15 units.
    private Map<String, Integer> distancesByGate;

    public ParkingSlot(String slotId, SlotType slotType, Map<String, Integer> distancesByGate) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.distancesByGate = distancesByGate;
        this.isAvailable = true;
    }

    public String getSlotId() {
        return slotId;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getDistanceFromGate(String gateId) {
        return distancesByGate.getOrDefault(gateId, Integer.MAX_VALUE);
    }

    public boolean isCompatible(VehicleType vType, SlotType requestedSlotType) {
        // If they requested a specific broader slot, respect the fallback minimums.
        // We ensure a vehicle does not park in a slot smaller than itself.
        if (vType == VehicleType.TWO_WHEELER) {
            return slotType == SlotType.SMALL || slotType == SlotType.MEDIUM || slotType == SlotType.LARGE;
        } else if (vType == VehicleType.CAR) {
            return slotType == SlotType.MEDIUM || slotType == SlotType.LARGE;
        } else if (vType == VehicleType.BUS) {
            return slotType == SlotType.LARGE;
        }
        return false;
    }
}
