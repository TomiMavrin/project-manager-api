package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Board;
import java.util.List;
import java.util.UUID;

public interface BoardDao {
    int createBoard(Board board, UUID userId);

    List<Board> getAllUserBoards(UUID userId);

    List<UUID> getBoardsUsers(UUID boardId);

    List<UUID> getNonBoardsUsers(UUID boardId);

    int addUserToBoard(UUID userId, UUID boardId);

    int removeUserFromBoard(UUID userId, UUID boardId);

    boolean checkBoard(UUID boardId, UUID userId);

    Board getBoard(UUID boardID);

    int editBoard(UUID boardID);

    int deleteBoard(UUID boardID, UUID userId);

}
