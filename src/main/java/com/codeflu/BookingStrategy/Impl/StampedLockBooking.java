package com.codeflu.BookingStrategy.Impl;
import com.codeflu.BookingStrategy.BookingStrategy;
import com.codeflu.Model.SeatInventory;

import java.util.concurrent.locks.StampedLock;

public class StampedLockBooking implements BookingStrategy {
    private final SeatInventory inventory;
    private final StampedLock lock = new StampedLock();

    public StampedLockBooking(SeatInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean bookSeat(int seatId) {
        long stamp = lock.writeLock(); // Exclusive
        try {
            if (inventory.isAvailable(seatId)) {
                System.out.println(Thread.currentThread().getName() + " -> Booked seat " + seatId);
                return inventory.reserve(seatId);
            } else {
                System.out.println(Thread.currentThread().getName() + " -> Seat " + seatId + " already taken");
                return false;
            }
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public boolean checkSeat(int seatId) {
        long stamp = lock.tryOptimisticRead(); // Optimistic read
        boolean available = inventory.isAvailable(seatId);
        if (!lock.validate(stamp)) { // Check if a write happened during read
            // Fall back to read lock
            stamp = lock.readLock();
            try {
                available = inventory.isAvailable(seatId);
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return available;
    }
}
