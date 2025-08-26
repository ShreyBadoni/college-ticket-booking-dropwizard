package com.codeflu.Dao;

import com.codeflu.Model.Event;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class EventDAO {
    private final Map<Long, Event> events = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Event create(Event event) {
        long id = idGenerator.getAndIncrement();
        event.setId(id);
        event.setBookedSeats(0); // default booked seats
        events.put(id, event);
        return event;
    }

    public Optional<Event> getById(Long id) {
        return Optional.ofNullable(events.get(id));
    }

    public List<Event> getAll() {
        return new ArrayList<>(events.values());
    }

    public Optional<Event> update(Long id, Event updatedEvent) {
        if (events.containsKey(id)) {
            updatedEvent.setId(id);
            events.put(id, updatedEvent);
            return Optional.of(updatedEvent);
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        return events.remove(id) != null;
    }
}

