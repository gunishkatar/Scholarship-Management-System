package com.dal.group7.persistent.implementations;

import com.dal.group7.config.ApplicationConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {

    static Connection connection;
    private static final ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(applicationConfiguration.getDbUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
