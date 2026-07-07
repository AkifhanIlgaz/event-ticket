package com.zozak.event_ticket.repositories;

import com.zozak.event_ticket.domain.entities.Event;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {}
