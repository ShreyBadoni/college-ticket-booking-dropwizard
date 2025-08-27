package com.codeflu.Services;


import com.codeflu.Dao.BookingDAO;
import com.codeflu.Model.Booking;
import com.codeflu.Model.Event;
import com.codeflu.Model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class BookingService {
    private final BookingDAO bookingDAO;
    private final EventService eventService;
    private final UserService userService;


    public BookingService(BookingDAO bookingDAO, EventService eventService,UserService userService) {
        this.bookingDAO = bookingDAO;
        this.eventService = eventService;
        this.userService=userService;
    }

    public Booking createBooking(Long eventId, Long userId, int seats) {
        // 1. Validate event
        Optional<Event> eventOpt = eventService.getEventById(eventId);
        if (eventOpt.isEmpty()) return null;
        Event event = eventOpt.get();

        // No booking for past events
        if (event.getEventDate().isBefore(LocalDateTime.now())) {
                    return null;        }

        // Not enough seats
        if (event.getBookedSeats() + seats > event.getTotalSeats()) {
            return null;
        }

        // 2. Validate user
        Optional<User> userOpt = userService.getUserById(userId);
        if (userOpt.isEmpty()) {
            return null;
        }

        // 3. All good → update seats & create booking
        event.setBookedSeats(event.getBookedSeats() + seats);

        Booking booking = new Booking();
        booking.setEventId(eventId);
        booking.setUserId(userId);
        booking.setSeatsBooked(seats);

        return bookingDAO.create(booking);
    }


    public List<Booking> getAllBookings() {
        return bookingDAO.getAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingDAO.getById(id);
    }

    public boolean deleteBooking(Long bookingId) {
        Optional<Booking> bookingOpt = bookingDAO.getById(bookingId);

        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();

            // free up seats
            Event event = eventService.getEventById(booking.getEventId())
                    .orElseThrow(() -> new RuntimeException("Event not found"));
            event.setBookedSeats(event.getBookedSeats() - booking.getSeatsBooked());
            eventService.updateEvent(event.getId(),event);

            return bookingDAO.delete(bookingId);
        }
        return false;
    }

}
