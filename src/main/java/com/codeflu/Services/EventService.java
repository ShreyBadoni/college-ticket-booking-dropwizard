package com.codeflu.Services;

import com.codeflu.Dao.EventDAO;
import com.codeflu.Model.Event;

import java.util.List;
import java.util.Optional;

public class EventService {
    private final EventDAO eventDAO;

    public EventService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public Event createEvent(Event event) {
        return eventDAO.create(event);
    }

    public List<Event> getAllEvents() {
        return eventDAO.getAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventDAO.getById(id);
    }

    public Optional<Event> updateEvent(Long id, Event updatedEvent) {
        return eventDAO.update(id, updatedEvent);
    }

    public boolean deleteEvent(Long id) {
        return eventDAO.delete(id);
    }
}
