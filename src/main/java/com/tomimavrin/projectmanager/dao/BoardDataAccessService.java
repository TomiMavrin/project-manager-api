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
        final String transaction ="WITH rows AS ( " +
                "INSERT INTO boards (name, description) VALUES (?, ?) RETURNING id)" +
                "INSERT INTO users_boards (board_id, user_id) SELECT id, ? from rows;";
        return jdbcTemplate.update(transaction , board.getName(),board.getDescription(), userId );
    }

    @Override
    public List<Board> getAllUserBoards() {
        return null;
    }

    @Override
    public Optional<Board> getBoard(UUID boardID) {
        return Optional.empty();
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
