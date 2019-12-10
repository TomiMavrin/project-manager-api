package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Board;
import com.tomimavrin.projectmanager.model.Column;
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
    public int createColumn(Column column) {
        final String q ="INSERT INTO columns (name, color, board_id) VALUES(?, ?, ?)";
        return jdbcTemplate.update(q , column.getName(), column.getColor(), column.getBoard_id());
    }

    @Override
    public List<Column> getBoardColumns(UUID boardId) {
        final String query = "SELECT * FROM columns WHERE board_id=?";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            UUID uuid = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String category = resultSet.getString("color");
            return new Column(uuid, name,category);
        }, boardId);
    }

    @Override
    public int editColumn(UUID columnId) {
        return 0;
    }

    @Override
    public int deleteColumn(UUID columnId) {
        return 0;
    }
}
