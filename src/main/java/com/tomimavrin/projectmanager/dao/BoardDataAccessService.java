package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Board;
import com.tomimavrin.projectmanager.model.User;
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
        final String transaction ="BEGIN;" +
        "CREATE TEMPORARY TABLE bid (id UUID) ON COMMIT DROP; " +
        "WITH rows AS ( " +
        "INSERT INTO boards (name, description) VALUES (?, ?) RETURNING id)" +
        "INSERT INTO bid (id) SELECT id FROM rows; " +
        "INSERT INTO users_boards (board_id, user_id) SELECT id, ? from bid; " +
        "INSERT INTO columns (name, board_id) SELECT ?, id from bid; " +
        "INSERT INTO columns (name, board_id) SELECT ?, id from bid; " +
        "INSERT INTO columns (name, board_id) SELECT ?, id from bid; " +
        "COMMIT;";
        return jdbcTemplate.update(transaction , board.getName(),board.getDescription(), userId, "To Do", "In Progress", "Done" );
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
    public List<UUID> getBoardsUsers(UUID boardId) {
        final String query = "SELECT * FROM users_boards WHERE board_id = ?";
        return jdbcTemplate.query(query, (resultSet, i) -> UUID.fromString(resultSet.getString("user_id")), boardId);
    }

    @Override
    public int addUserToBoard(UUID userId, UUID boardId) {
        final String query = "INSERT INTO users_boards (user_id, board_id) VALUES (?, ?) ON CONFLICT DO NOTHING;";
        return jdbcTemplate.update(query, userId, boardId);
    }

    @Override
    public int removeUserFromBoard(UUID userId, UUID boardId) {
        final String query = "DELETE FROM users_boards WHERE user_id=? AND board_id=?";
        return jdbcTemplate.update(query, userId, boardId);
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
    public int deleteBoard(UUID boardID, UUID userId) {
        String action = "BEGIN;" +
                "DELETE FROM users_boards WHERE board_id=? AND user_id=?;" +
                "DELETE FROM columns where board_id=?;" +
                "DELETE FROM boards WHERE id=?;" +
                "COMMIT;";
        return jdbcTemplate.update(action, boardID, userId, boardID, boardID);
    }
}
