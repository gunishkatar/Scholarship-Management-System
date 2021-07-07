package com.dal.group7.shared.db;

import com.dal.group7.config.ApplicationConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private final ApplicationConfiguration applicationConfiguration;

    public ConnectionManager(ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
    }

    public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(applicationConfiguration.getDbUrl());
    }
}
