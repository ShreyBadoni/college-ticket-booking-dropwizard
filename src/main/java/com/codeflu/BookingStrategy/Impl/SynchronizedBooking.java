package com.codeflu.BookingStrategy.Impl;

import com.codeflu.BookingStrategy.BookingStrategy;
import com.codeflu.Model.SeatInventory;

public class SynchronizedBooking implements BookingStrategy {
    private final SeatInventory inventory;

    public SynchronizedBooking(SeatInventory inventory) {
        this.inventory = inventory;
    }


    @Override
    public boolean bookSeat(int seatId) {
        synchronized(this){
        if (inventory.isAvailable(seatId)) {
            return inventory.reserve(seatId);
        }
        return false;
        }
    }
}
