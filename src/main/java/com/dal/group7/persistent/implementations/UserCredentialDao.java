package com.dal.group7.persistent.implementations;

import com.dal.group7.constants.SQLConstants;
import com.dal.group7.constants.TableConstants;
import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Institute;
import com.dal.group7.persistent.model.UserCredential;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dal.group7.constants.FieldConstants.PARAMETER_INDEX_ID;
import static com.dal.group7.constants.SQLConstants.*;

public class UserCredentialDao extends Dao<String, UserCredential> {

    private static final String USER = TableConstants.USER_CREDENTIAL;
    private final ConnectionManager connectionManager;

    public UserCredentialDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Boolean doesUserExist(String id) throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(getSelectByUserIdQuery(USER))) {
            preparedStatement.setString(PARAMETER_INDEX_ID, id);
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }

    }


    public Optional<UserCredential> get(String id) throws SQLException {
        try(var connection = connectionManager.getConnection();
            var preparedStatement = connection.prepareStatement(getSelectByUserIdQuery(USER))) {
            preparedStatement.setString(PARAMETER_INDEX_ID, id);
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? Optional.of(new UserCredential().from(resultSet)) : Optional.empty();
        }
    }

}
