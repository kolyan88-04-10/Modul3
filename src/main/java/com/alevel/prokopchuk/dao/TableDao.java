package com.alevel.prokopchuk.dao;

import com.alevel.prokopchuk.models.Column;
import com.alevel.prokopchuk.models.Node;
import com.alevel.prokopchuk.models.Table;

import java.util.Map;

public class TableDao extends AbstractDao<Table> {
    public static final String SQL_INSERT_INTO_TABLE = "INSERT INTO ";

    @Override
    public boolean create(Table model) {
        return false;
    }

    @Override
    public boolean delete(Table model) {
        return false;
    }

    public boolean addNode(Table table, Node node) {
        StringBuilder queryBuilder = new StringBuilder(SQL_INSERT_INTO_TABLE);
        StringBuilder valuesBuilder = new StringBuilder();
        for (Map.Entry<Column, String> entry : node.getValues().entrySet()) {
            queryBuilder.append(entry.getKey().getName()).append(", ");
            valuesBuilder.append(entry.getValue()).append(", ");
        }
        return false;
    }
}
