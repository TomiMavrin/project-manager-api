package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("boards")
public class BoardDataAccessService implements BoardDao {

    private final JdbcTemplate jdbcTemplate;

    public BoardDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int createBoard(Board board, UUID userId) {
        final String transaction ="BEGIN; WITH rows AS ( " +
                "INSERT INTO boards (name, description) VALUES (?, ?) RETURNING id)" +
                "INSERT INTO users_boards (board_id, user_id) SELECT id, ? from rows; COMMIT;";
        return jdbcTemplate.update(transaction , board.getName(),board.getDescription(), userId );
    }

    @Override
    public List<Board> getAllUserBoards(UUID userId) {
        final String q = "SELECT id,name,description FROM boards INNER JOIN users_boards ON (users_boards.board_id = boards.id) WHERE users_boards.user_id=?";
        return jdbcTemplate.query(q, (resultSet, i) -> {
            UUID uuid = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            return new Board(uuid, name,description);
        }, userId);
    }

    @Override
    public boolean checkBoard(UUID boardId, UUID userId) {
        int count = 0;
        final String q = "SELECT count(*) FROM users_boards WHERE board_id = ? AND user_id=?";
        List<String> list = jdbcTemplate.query(q, (resultSet, i) -> {
            return "-";
        },boardId, userId);
        return list.size() > 0;
    }

    @Override
    public Board getBoard(UUID boardID) {
        final String query = "SELECT * FROM boards WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{boardID}, (rs, rowNum) ->
                new Board(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("description")
                ));
    }

    @Override
    public int editBoard(UUID boardID) {
        return 0;
    }

    @Override
    public int deleteBoard(UUID boardID) {
        return 0;
    }
}
