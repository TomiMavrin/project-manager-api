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
    public Ticket createTicket(Ticket ticket, UUID userId) {
        final String q = "INSERT INTO TICKETS (column_id, title, subtitle, description, color, date_due, created_by, assigned_to) VALUES(?, ?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING id,title,subtitle,description,color,date_created,date_due,column_id,created_by,assigned_to";
        return jdbcTemplate.query(q, (rs, rowNum) -> {
                String assignedString = rs.getString("assigned_to");
                UUID assigned_to = null;
                if(assignedString != null){
                    assigned_to = UUID.fromString(assignedString);
                }
                return new Ticket(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("title"),
                        rs.getString("subtitle"), rs.getString("description"),
                        rs.getString("color"),
                        rs.getTimestamp("date_created"),
                        rs.getTimestamp("date_due"),
                        UUID.fromString(rs.getString("column_id")),
                        UUID.fromString(rs.getString("created_by")),
                        assigned_to
                );
            }, ticket.getColumn_id(),ticket.getTitle(), ticket.getSubtitle(), ticket.getDescription(),ticket.getColor(), ticket.getDate_due(), userId, ticket.getAssigned_to()).get(0);
    }

    @Override
    public List<Ticket> getColumnTickets(UUID columnId) {
        final String q = "SELECT * FROM TICKETS WHERE column_id = ?";
        return jdbcTemplate.query(q, (resultSet, i) -> {
            UUID uuid = UUID.fromString(resultSet.getString("id"));
            UUID column = UUID.fromString(resultSet.getString("column_id"));
            String title = resultSet.getString("title");
            String subtitle = resultSet.getString("subtitle");
            String description = resultSet.getString("description");
            String color = resultSet.getString("color");
            Timestamp date_created = resultSet.getTimestamp("date_created");
            Timestamp date_due = resultSet.getTimestamp("date_due");
            UUID created_by = UUID.fromString(resultSet.getString("created_by"));
            String assignedString = resultSet.getString("assigned_to");
            UUID assigned_to = null;
            if(assignedString != null){
                assigned_to = UUID.fromString(assignedString);
            }
            return new Ticket(uuid, title, subtitle, description, color, date_created, date_due, column, created_by, assigned_to);
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
                "RETURNING id,title,description,date_created,column_id,created_by,subtitle, assigned_to";
        return jdbcTemplate.query(updateQ, (rs, rowNum) ->{
                String assignedString = rs.getString("assigned_to");
                UUID assigned_to = null;
                if(assignedString != null){
                    assigned_to = UUID.fromString(assignedString);
                }
                return new Ticket(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("title"),
                        rs.getString("subtitle"),
                        rs.getString("description"),
                        rs.getString("color"),
                        rs.getTimestamp("date_created"),
                        rs.getTimestamp("date_due"),
                        UUID.fromString(rs.getString("column_id")),
                        UUID.fromString(rs.getString("created_by")),
                        assigned_to
                );
            }, ticket.getColumn_id(), ticket.getId()).get(0);
    }
}
