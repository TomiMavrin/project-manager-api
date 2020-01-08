package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Column;
import com.tomimavrin.projectmanager.model.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("columns")
public class ColumnDataAccessService implements ColumnDao {

    private final JdbcTemplate jdbcTemplate;

    public ColumnDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Column createColumn(Column column) {
        final String q ="INSERT INTO columns (name, board_id) VALUES(?, ?)" +
                "RETURNING id,name,board_id";
        return jdbcTemplate.query(q, (rs, rowNum) ->
                new Column(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("name"),
                        UUID.fromString(rs.getString("board_id"))
                ), column.getName(), column.getBoard_id()).get(0);
    }

    @Override
    public List<Column> getBoardColumns(UUID boardId) {
        final String query = "SELECT * FROM columns WHERE board_id=?";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            UUID uuid = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Column(uuid, name, boardId);
        }, boardId);
    }

    @Override
    public int editColumn(UUID columnId) {
        return 0;
    }

    @Override
    public int deleteColumn(UUID columnId) {
        final String query = "DELETE FROM columns WHERE id = ?";
        return jdbcTemplate.update(query, columnId);
    }
}
