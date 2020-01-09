package com.tomimavrin.projectmanager.service;

import com.tomimavrin.projectmanager.dao.BoardDao;
import com.tomimavrin.projectmanager.model.Board;
import com.tomimavrin.projectmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService {

    private final BoardDao boardDao;

    @Autowired
    public BoardService(@Qualifier("boards") BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public int createBoard(Board board, UUID userId){
        return this.boardDao.createBoard(board, userId);
    }

    public List<Board> getAllUserBoards(UUID id){
        return boardDao.getAllUserBoards(id);
    }

    public boolean checkBoard(UUID boardId, UUID userId){
        return this.boardDao.checkBoard(boardId, userId);
    }

    public Board getBoard(UUID boardId){
        return this.boardDao.getBoard(boardId);
    }

    public int deleteBoard(UUID boardId, UUID userId){
        return this.boardDao.deleteBoard(boardId, userId);
    }

    public int editBoard(UUID id){
        return this.boardDao.editBoard(id);
    }

    public List<UUID> getBoardsUsers(UUID boardId){
        return this.boardDao.getBoardsUsers(boardId);
    }

    public int addUserToBoard(UUID userId, UUID boardId) {
        return this.boardDao.addUserToBoard(userId,boardId);
    }

    public int removeUserFromBoard(UUID userId, UUID boardId) {
        return this.boardDao.removeUserFromBoard(userId, boardId);
    }

}
