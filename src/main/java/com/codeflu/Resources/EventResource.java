package com.codeflu.Resources;


import com.codeflu.Model.Event;
import com.codeflu.Services.EventService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    private final EventService eventService;

    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    // Create Event
    @POST
    public Response createEvent(@Valid Event event) {
        Event created = eventService.createEvent(event);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    // Get All Events
    @GET
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // Get Event by ID
    @GET
    @Path("/{id}")
    public Response getEventById(@PathParam("id") Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(value -> Response.ok(value).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    // Update Event
    @PUT
    @Path("/{id}")
    public Response updateEvent(@PathParam("id") Long id,@Valid Event updatedEvent) {
        Optional<Event> event = eventService.updateEvent(id, updatedEvent);
        return event.map(value -> Response.ok(value).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    // Delete Event
    @DELETE
    @Path("/{id}")
    public Response deleteEvent(@PathParam("id") Long id) {
        boolean deleted = eventService.deleteEvent(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
