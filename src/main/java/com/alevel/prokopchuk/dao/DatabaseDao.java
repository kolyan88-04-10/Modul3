package com.alevel.prokopchuk.dao;

import com.alevel.prokopchuk.entities.Table;

public class DatabaseDao extends AbstractDao<Integer, Table> {

    public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ? (" +
            "?)";
            //"  UNIQUE INDEX `` (`id` ASC))";
    public boolean create(Table entity) {
        return false;
    }

    public boolean delete(Table entity) {
        return false;
    }
}
