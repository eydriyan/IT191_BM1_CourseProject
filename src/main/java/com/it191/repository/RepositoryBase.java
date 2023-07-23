package com.it191.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class RepositoryBase {
    private String USERNAME = "it191_courseproject";
    private String PASSWORD = "it191_courseproject";
    private final String LINK = "jdbc:mysql://localhost:3306?useTimezone=true&serverTimezone=UTC";
    private final String CONNECTOR = "com.mysql.cj.jdbc.Driver";

    public RepositoryBase() {
        try { Class.forName(CONNECTOR); } catch(ClassNotFoundException ex) {}
    }

    protected Connection getSqlConnection() {
        try {
            return DriverManager.getConnection(LINK, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
