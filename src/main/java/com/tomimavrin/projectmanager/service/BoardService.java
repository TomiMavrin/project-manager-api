package com.tomimavrin.projectmanager.service;

import com.tomimavrin.projectmanager.dao.BoardDao;
import com.tomimavrin.projectmanager.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Board> getAllUserBoards(){
        return boardDao.getAllUserBoards();
    }

    public int deleteBoard(UUID id){
        return this.boardDao.deleteBoard(id);
    }

    public int editBoard(UUID id){
        return this.boardDao.editBoard(id);
    }

}
