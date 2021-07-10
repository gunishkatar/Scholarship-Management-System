package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.interfaces.Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class PwdEncryptDao extends Dao {
    private final ConnectionManager connectionManager;
    String encryptionLookupQuery = "select * from encryption_logic";

    public PwdEncryptDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    public HashMap<String, String> getValue() throws SQLException {
        try(var connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(encryptionLookupQuery)) {
            final var resultSet = preparedStatement.executeQuery();
            HashMap<String, String> map = new HashMap<String, String>();
            while (resultSet.next()) {
                map.put(resultSet.getString("character"),resultSet.getString("hash"));
            }
            return map;
        }
    }

}
