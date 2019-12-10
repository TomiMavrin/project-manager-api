package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Column;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("columns")
public class ColumnDataAccessService implements ColumnDao {
    @Override
    public int createColumn(Column column) {
        return 0;
    }

    @Override
    public List<Column> getBoardColumns(UUID boardId) {
        return null;
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
