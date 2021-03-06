package com.alevel.prokopchuk;

import com.alevel.prokopchuk.dao.DatabaseDao;
import com.alevel.prokopchuk.dao.NodeDao;
import com.alevel.prokopchuk.dao.TableDao;
import com.alevel.prokopchuk.models.Column;
import com.alevel.prokopchuk.models.Database;
import com.alevel.prokopchuk.models.Table;

public class Main {
    public static void main(String[] args) {
        DatabaseDao databaseDao = new DatabaseDao();
        TableDao tableDao = new TableDao();
        NodeDao nodeDao = new NodeDao();
        Database database = new Database("Database1", databaseDao, tableDao, nodeDao);
        Table table2 = new Table("Table2", tableDao, nodeDao);
        database.addTable(table2);
        Column column1 = new Column("column1", "VARCHAR(100)");
        Column column2 = new Column("column2", "VARCHAR(100)");
        table2.addColumn(column1).addColumn(column2);
        Table.Node node1 = table2.newNode();
        node1.insert(column1, "value1 column1");
        node1.insert(column2, "value1 column2");

        Table.Node node2 = table2.newNode();
        node2.insert(column1, "value2 column1");
        node2.insert(column2, "value2 column2");

        Table.Node node3 = table2.newNode();
        node3.insert(column1, "value3 column1");
        node3.insert(column2, "value3 column2");
        

        table2.removeNode(node2);
        table2.renameColumn(column1, "column1renamed");
        table2.removeColumn(column1);
        database.removeTable(table2);
    }
}
