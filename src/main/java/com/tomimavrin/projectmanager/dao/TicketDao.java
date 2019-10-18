package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketDao {

    int createTicket(UUID id, Ticket ticket);

    default int createTicket(Ticket ticket){
        UUID id = UUID.randomUUID();
        return createTicket(id, ticket);
    };

    List<Ticket> getAllTickets(UUID columnID);

    Optional<Ticket> getTicket(UUID ticketID);

    int editTicket(UUID ticketId);

    int deleteTicket(UUID ticketId);
}
