package com.codeflu.Dao;


import com.codeflu.Model.Booking;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class BookingDAO {
    private final Map<Long, Booking> bookings = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public Booking create(Booking booking) {
        long id = idCounter.getAndIncrement();
        booking.setId(id);
        bookings.put(id, booking);
        return booking;
    }

    public List<Booking> getAll() {
        return new ArrayList<>(bookings.values());
    }

    public Optional<Booking> getById(Long id) {
        return Optional.ofNullable(bookings.get(id));
    }

    public boolean delete(Long id) {
        return bookings.remove(id) != null;
    }
}

