package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketDao {

    int createTicket(Ticket ticket);

    List<Ticket> getColumnTickets(UUID columnId);

    Optional<Ticket> getTicket(UUID ticketID);

    int editTicket(UUID ticketId);

    int deleteTicket(UUID ticketId);
}
