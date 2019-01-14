package com.alevel.prokopchuk.dao;

import com.alevel.prokopchuk.ConnectorDB;
import com.alevel.prokopchuk.models.Column;
import com.alevel.prokopchuk.models.Table;

import java.sql.*;

public class TableDao extends AbstractDao<Table> {
    private static final String SQL_INSERT_INTO_TABLE = "INSERT INTO ";
    private static final String SQL_DELETE_RECORD = "DELETE FROM %s WHERE ID = %d;";
    private static final String SQL_INSERT_COLUMN_INTO_TABLE = "ALTER TABLE %s ADD %s %s;";
    private static final String SQL_REMOVE_COLUMN_FROM_TABLE = "ALTER TABLE %s DROP COLUMN %s;";
    private static final String SQL_RENAME_COLUMN = "ALTER TABLE %s RENAME COLUMN %s TO %s;";
    private static final String SQL_GET_COLUMNS = "SELECT COLUMN_NAME, DATA_TYPE FROM information_schema.columns\n" +
            "WHERE TABLE_NAME = ?;";

    @Override
    public boolean create(Table model) {
        return false;
    }

    @Override
    public boolean delete(Table model) {
        return false;
    }

    public boolean addNode(Table table, Table.Node node) {
        StringBuilder queryBuilder = new StringBuilder(SQL_INSERT_INTO_TABLE);
        queryBuilder.append(table.getName()).append(" (")
        .append("ID").append(')').append(" VALUES (").append(node.getId()).append(");");
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(queryBuilder.toString());
            System.out.println(node + " was added to table: " + table.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeNode(Table table, Table.Node node) {
        String query = String.format(SQL_DELETE_RECORD, table.getName(), node.getId());

        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println(node + " was removed from table: " + table.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addColumn(Table table, Column column) {
        String query = String.format(SQL_INSERT_COLUMN_INTO_TABLE, table.getName(),
                column.getName(), column.getTypeName());

        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println(column + " was added to table: " + table.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeColumn(Table table, Column column) {
        String query = String.format(SQL_REMOVE_COLUMN_FROM_TABLE, table.getName(),
                column.getName());
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println(column + " was removed from table: " + table.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean renameColumn(Table table, Column column, String newName) {
        String query = String.format(SQL_RENAME_COLUMN, table.getName(),
                column.getName(), newName);
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println(column + " was in table: " + table.getName()
                    + " was renamed to " + newName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void loadTable(Table table) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_COLUMNS)){
            ps.setString(1, table.getName());
            ResultSet rs = ps.executeQuery();
            Column column;
            rs.next();
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String columnType = rs.getString("DATA_TYPE");
                column = new Column(columnName, columnType);
                table.addColumn(column);
            }
            System.out.println(column + " was in table: " + table.getName()
                    + " was renamed to " + newName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
