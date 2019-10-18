package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class TicketDataAccessService implements TicketDao {

    private final JdbcTemplate jdbcTemplate;

    public TicketDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int createTicket(UUID id, Ticket ticket) {
        final String q = "INSERT INTO TICKETS (id, title, description, priority) VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(q, id,ticket.getTitle(), ticket.getDescription(), ticket.getPriority());
    }

    @Override
    public List<Ticket> getAllTickets(UUID columnID) {
        return null;
    }

    @Override
    public Optional<Ticket> getTicket(UUID ticketID) {
        return Optional.empty();
    }

    @Override
    public int editTicket(UUID ticketId) {
        return 0;
    }

    @Override
    public int deleteTicket(UUID ticketId) {
        return 0;
    }
}
