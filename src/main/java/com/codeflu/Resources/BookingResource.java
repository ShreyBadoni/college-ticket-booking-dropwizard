package com.codeflu.Resources;


import com.codeflu.Model.Booking;
import com.codeflu.Model.Event;
import com.codeflu.Services.BookingService;
import com.codeflu.Services.EventService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {

    private final BookingService bookingService;
    private final EventService eventService;

    public BookingResource(BookingService bookingService, EventService eventService) {
        this.bookingService = bookingService;
        this.eventService = eventService;
    }

    // Book seats for an event
    @POST
    @Path("/{eventId}")
    public Response bookSeats(@PathParam("eventId") Long eventId, @PathParam("seats") int seats) {
        // Check if event exists
        Optional<Event> eventOpt = eventService.getEventById(eventId);
        if (eventOpt.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Event not found with id " + eventId)
                    .build();
        }

        Event event = eventOpt.get();

        // Check seat availability
        if (event.getBookedSeats() + seats > event.getTotalSeats()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Booking failed: not enough seats")
                    .build();
        }

        // Create booking object
        Booking booking = new Booking();
        booking.setEventId(eventId);
        booking.setSeatsBooked(seats);

        Booking createdBooking = bookingService.createBooking(booking);

        // Update event booked seats (optional business rule)
        event.setBookedSeats(event.getBookedSeats() + seats);
        eventService.updateEvent(eventId, event);

        return Response.ok(createdBooking).build();
    }

    // Get all bookings
    @GET
    public Response getAllBookings() {
        return Response.ok(bookingService.getAllBookings()).build();
    }

    // Get booking by ID
    @GET
    @Path("/{id}")
    public Response getBookingById(@PathParam("id") Long id) {
        return bookingService.getBookingById(id)
                .map(b -> Response.ok(b).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).entity("Booking not found").build());
    }

    // Cancel booking
    @DELETE
    @Path("/{id}")
    public Response deleteBooking(@PathParam("id") Long id) {
        boolean deleted = bookingService.deleteBooking(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Booking not found")
                    .build();
        }
    }
}
