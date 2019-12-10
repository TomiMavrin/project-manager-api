package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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
    public int createTicket(Ticket ticket) {
        final String q = "INSERT INTO TICKETS (column_id, title, description, created_by) VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(q, ticket.getColumnId(),ticket.getTitle(), ticket.getDescription(), ticket.getCreatedBy());
    }

    @Override
    public List<Ticket> getColumnTickets(UUID columnId) {
        final String q = "SELECT * FROM TICKETS WHERE column_id = ?";
        return jdbcTemplate.query(q, (resultSet, i) -> {
            UUID uuid = UUID.fromString(resultSet.getString("id"));
            UUID column = UUID.fromString(resultSet.getString("column_id"));
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            Timestamp date_created = resultSet.getTimestamp("date_created");
            UUID created_by = UUID.fromString(resultSet.getString("created_by"));
            return new Ticket(uuid, title,description, date_created, column, created_by);
        }, columnId);
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
