package com.zozak.event_ticket.mappers;

import com.zozak.event_ticket.domain.CreateEventRequest;
import com.zozak.event_ticket.domain.CreateTicketTypeRequest;
import com.zozak.event_ticket.domain.dtos.CreateEventRequestDto;
import com.zozak.event_ticket.domain.dtos.CreateEventResponseDto;
import com.zozak.event_ticket.domain.dtos.CreateTicketTypeRequestDto;
import com.zozak.event_ticket.domain.dtos.ListEventResponseDto;
import com.zozak.event_ticket.domain.dtos.ListEventTicketTypeResponseDto;
import com.zozak.event_ticket.domain.entities.Event;
import com.zozak.event_ticket.domain.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EventMapper {
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);
    CreateEventRequest fromDto(CreateEventRequestDto dto);
    CreateEventResponseDto toDto(Event event);

    ListEventTicketTypeResponseDto toDto(TicketType ticketType);

    ListEventResponseDto toListEventResponseDto(Event event);
}
