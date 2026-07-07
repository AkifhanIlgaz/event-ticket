package com.zozak.event_ticket.services;

import com.zozak.event_ticket.domain.CreateEventRequest;
import com.zozak.event_ticket.domain.entities.Event;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);
    Page<Event> listEventsForOrganizer(UUID organizerId, Pageable pageable);
}
