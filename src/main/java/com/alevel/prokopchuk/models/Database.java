package com.alevel.prokopchuk.models;

import com.alevel.prokopchuk.dao.DatabaseDao;

import java.util.ArrayList;
import java.util.List;

public class Database extends Model {
    private List<Table> tables = new ArrayList<>();
    private DatabaseDao databaseDao;

    public Database(String name, DatabaseDao databaseDao) {
        super(name);
        this.databaseDao = databaseDao;
    }

    public void addTable(Table table){
        tables.add(table);
        databaseDao.create(table);
    }

    public void removeTable(Table table){
        databaseDao.delete(table);
        tables.remove(table);
    }

    public List<Table> getTables() {

        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
