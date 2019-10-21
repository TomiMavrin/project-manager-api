package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Ticket;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("tickets")
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
    public List<Ticket> getAllTickets() {
        final String q = "SELECT * FROM TICKETS";
        return jdbcTemplate.query(q, (resultSet, i) -> {
            UUID uuid = UUID.fromString(resultSet.getString("id"));
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            int priority = resultSet.getInt("priority");
            return new Ticket(uuid, title,description, priority);
        });
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
