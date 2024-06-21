package com.soupgroup.liberalis.liberalisplugin;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseSingleton {
    private static DatabaseSingleton single_database = null;

//    private static String DATABASE_URL = "jdbc:sqlite:plugins/LiberalisPlugin/database.db";
    private Connection database_connection;

    private DatabaseSingleton () {
        //        Class.forName("org.sqlite.JDBC")
//        this.database_connection = DriverManager.getConnection();
    }

    public static synchronized DatabaseSingleton getInstance(){
        if (single_database==null) single_database = new DatabaseSingleton();
        return single_database;
    }

    public void connect() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc://mysql://localhost:3306/mydatabase"); // Address of your running MySQL database
        config.setUsername("username"); // Username
        config.setPassword("password"); // Password
        config.setMaximumPoolSize(10); // Pool size defaults to 10

        config.addDataSourceProperty("", ""); // MISC settings to add
        HikariDataSource dataSource = new HikariDataSource(config);

        try (Connection connection = dataSource.getConnection()) {
            // Use a try-with-resources here to autoclose the connection.
            PreparedStatement sql = connection.prepareStatement("SQL");
            // Execute statement
        } catch (Exception e) {
            // Handle any exceptions that arise from getting / handing the exception.
        }
    }

    public boolean loginPlayer() {
        return false;
    }

    public boolean registerPlayer() {
        return false;
    }

}
