package com.codeflu.Model;

import java.util.HashSet;
import java.util.Set;

public class SeatInventory {
    private final Set<Integer> availableSeats;

    public SeatInventory(int totalSeats) {
        this.availableSeats = new HashSet<>();
        for (int i = 1; i <= totalSeats; i++) {
            availableSeats.add(i);
        }
    }

    public boolean isAvailable(int seatId) {
        return availableSeats.contains(seatId);
    }

    public boolean reserve(int seatId) {
        return availableSeats.remove(seatId);
    }

    public int remainingSeats() {
        return availableSeats.size();
    }
}
