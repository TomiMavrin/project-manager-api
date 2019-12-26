package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Board;
import com.tomimavrin.projectmanager.model.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository("tickets")
public class TicketDataAccessService implements TicketDao {

    private final JdbcTemplate jdbcTemplate;

    public TicketDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Ticket createTicket(Ticket ticket, UUID userId) {
        final String q = "INSERT INTO TICKETS (column_id, title, description, created_by) VALUES(?, ?, ?, ?) " +
                "RETURNING id,title,description,date_created,column_id,created_by";
        return jdbcTemplate.query(q, (rs, rowNum) ->
                new Ticket(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getTimestamp("date_created"),
                        UUID.fromString(rs.getString("column_id")),
                        UUID.fromString(rs.getString("created_by"))
                ), ticket.getColumn_id(),ticket.getTitle(), ticket.getDescription(), userId).get(0);
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
        final String query = "DELETE FROM tickets WHERE id = ?";
        return jdbcTemplate.update(query, ticketId);
    }

    @Override
    public Ticket moveTicket(Ticket ticket) {
        final String updateQ = "UPDATE tickets SET column_id=? WHERE id = ? "+
                "RETURNING id,title,description,date_created,column_id,created_by";
        return jdbcTemplate.query(updateQ, (rs, rowNum) ->
                new Ticket(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getTimestamp("date_created"),
                        UUID.fromString(rs.getString("column_id")),
                        UUID.fromString(rs.getString("created_by"))
                ), ticket.getColumn_id(), ticket.getId()).get(0);
    }
}
