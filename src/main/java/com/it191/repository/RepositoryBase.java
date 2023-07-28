package com.it191.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
            Connection sql_con = DriverManager.getConnection(LINK, USERNAME, PASSWORD);
            
            Statement SetDB = sql_con.createStatement();
            SetDB.executeUpdate("USE it191_course_project");

            return sql_con;
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
