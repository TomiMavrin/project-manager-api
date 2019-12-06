package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Board;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoardDao {
    int createBoard(Board board, UUID userId);

    List<Board> getAllUserBoards();

    Optional<Board> getBoard(UUID boardID);

    int editBoard(UUID boardID);

    int deleteBoard(UUID boardID);

}
