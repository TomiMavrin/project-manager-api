package com.tomimavrin.projectmanager.service;


import com.tomimavrin.projectmanager.dao.ColumnDao;
import com.tomimavrin.projectmanager.model.Column;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ColumnService {

    private final ColumnDao columnDao;

    public ColumnService(@Qualifier("columns") ColumnDao columnDao) {
        this.columnDao = columnDao;
    }

    public Column createColumn(Column column){
        return this.columnDao.createColumn(column);
    }

    public List<Column> getBoardColumns(UUID boardId){
        return this.columnDao.getBoardColumns(boardId);
    }

    public int editColumn(UUID columnId){
        return this.columnDao.editColumn(columnId);
    }

    public int deleteColumn(UUID columnId){
        return this.columnDao.deleteColumn(columnId);
    }
}
