package com.codeflu.Services;


import com.codeflu.Dao.BookingDAO;
import com.codeflu.Model.Booking;

import java.util.List;
import java.util.Optional;

public class BookingService {
    private final BookingDAO bookingDAO;
    private final EventService eventService;

    public BookingService(BookingDAO bookingDAO, EventService eventService) {
        this.bookingDAO = bookingDAO;
        this.eventService = eventService;
    }

    public Booking createBooking(Booking booking) {
        // Example: verify event exists before booking
        eventService.getEventById(booking.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return bookingDAO.create(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.getAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingDAO.getById(id);
    }

    public boolean deleteBooking(Long id) {
        return bookingDAO.delete(id);
    }
}
