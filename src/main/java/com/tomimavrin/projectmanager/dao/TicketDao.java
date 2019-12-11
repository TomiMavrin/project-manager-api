package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketDao {

    Ticket createTicket(Ticket ticket, UUID userId);

    List<Ticket> getColumnTickets(UUID columnId);

    int editTicket(UUID ticketId);

    int deleteTicket(UUID ticketId);
}
