package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.UserCredential;

import java.sql.SQLException;
import java.util.Optional;

import static com.dal.group7.constants.FieldConstants.ONE;
import static com.dal.group7.constants.SQLConstants.USER_CREDENTIAL;
import static com.dal.group7.constants.SQLConstants.getSelectByUserIdQuery;

public class UserCredentialDao extends Dao<String, UserCredential> {

    private final ConnectionManager connectionManager;

    public UserCredentialDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Boolean doesExist(String id) throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(
                     getSelectByUserIdQuery(USER_CREDENTIAL))) {
            preparedStatement.setString(ONE, id);
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }

    }

    @Override
    public Optional<UserCredential> get(String id) throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(
                     getSelectByUserIdQuery(USER_CREDENTIAL))) {
            preparedStatement.setString(ONE, id);
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next() ?
                    Optional.of(new UserCredential().from(resultSet)) :
                    Optional.empty();
        }
    }

}
