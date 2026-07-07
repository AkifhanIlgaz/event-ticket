package com.zozak.event_ticket.services.impl;

import com.zozak.event_ticket.domain.CreateEventRequest;
import com.zozak.event_ticket.domain.entities.Event;
import com.zozak.event_ticket.domain.entities.TicketType;
import com.zozak.event_ticket.exceptions.UserNotFoundException;
import com.zozak.event_ticket.repositories.EventRepository;
import com.zozak.event_ticket.repositories.UserRepository;
import com.zozak.event_ticket.services.EventService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        var organizer = userRepository
            .findById(organizerId)
            .orElseThrow(() ->
                new UserNotFoundException(
                    String.format("User with ID '%s' not found", organizerId)
                )
            );

        Event eventToCreate = new Event();

        var ticketTypesToCreate = event
            .getTicketTypes()
            .stream()
            .map(ticketType -> {
                TicketType ticketTypeToCreate = new TicketType();
                ticketTypeToCreate.setName(ticketType.getName());
                ticketTypeToCreate.setPrice(ticketType.getPrice());
                ticketTypeToCreate.setDescription(ticketType.getDescription());
                ticketTypeToCreate.setTotalAvailable(
                    ticketType.getTotalAvailable()
                );
                ticketTypeToCreate.setEvent(eventToCreate);
                return ticketTypeToCreate;
            })
            .toList();

        eventToCreate.setName(event.getName());
        eventToCreate.setStart(event.getStart());
        eventToCreate.setEnd(event.getEnd());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSalesStart(event.getSalesStart());
        eventToCreate.setSalesEnd(event.getSalesEnd());
        eventToCreate.setStatus(event.getStatus());
        eventToCreate.setOrganizer(organizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);
    }
}
