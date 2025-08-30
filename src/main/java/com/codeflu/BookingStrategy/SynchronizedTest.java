package com.codeflu.BookingStrategy;

import com.codeflu.BookingStrategy.Impl.ReentrantLockBooking;
import com.codeflu.BookingStrategy.Impl.SynchronizedBooking;
import com.codeflu.Model.SeatInventory;

import java.util.concurrent.*;

public class SynchronizedTest {
    public static void main(String[] args) throws InterruptedException {
        SeatInventory inventory = new SeatInventory(5);
        BookingStrategy strategy = new ReentrantLockBooking(inventory);

        ExecutorService pool = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            final int seatId = (i % 5) + 1; // multiple threads hit same seats
            pool.submit(() -> {
                boolean success = strategy.bookSeat(seatId);
                System.out.println(Thread.currentThread().getName() +
                        " trying seat " + seatId +
                        " -> " + (success ? "Booked" : "Failed"));
            });
        }

        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Remaining seats: " + inventory.remainingSeats());
    }
}
