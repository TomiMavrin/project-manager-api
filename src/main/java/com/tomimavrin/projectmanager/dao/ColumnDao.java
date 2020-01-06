package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Column;

import java.util.List;
import java.util.UUID;

public interface ColumnDao {

    Column createColumn(Column column);

    List<Column> getBoardColumns(UUID boardId);

    int editColumn(UUID columnId);

    int deleteColumn(UUID columnId);
}
