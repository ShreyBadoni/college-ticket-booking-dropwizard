package com.codeflu.BookingStrategy.Impl;

import com.codeflu.BookingStrategy.BookingStrategy;
import com.codeflu.Model.SeatInventory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockBooking implements BookingStrategy {
    private final SeatInventory inventory;
    private final Lock lock = new ReentrantLock();

    public ReentrantLockBooking(SeatInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean bookSeat(int seatId) {
        lock.lock();   // acquire lock
        try {
            if (inventory.isAvailable(seatId)) {
                return inventory.reserve(seatId);
            }
            return false;
        } finally {
            lock.unlock(); // always release in finally
        }
    }
}

