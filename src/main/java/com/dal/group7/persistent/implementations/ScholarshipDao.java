package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Scholarship;

import java.sql.SQLException;
import java.util.List;

import static com.dal.group7.constants.FieldConstants.ONE;
import static com.dal.group7.constants.FieldConstants.ZERO;
import static com.dal.group7.constants.SQLConstants.getInsertNewScholarship;

public class ScholarshipDao extends Dao<Integer, Scholarship> {

    private final ConnectionManager connectionManager;

    public ScholarshipDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void insertOne(Scholarship scholarship) throws SQLException {
        try(var connection = connectionManager.getConnection();
            var preparedStatement = connection.prepareStatement(getInsertNewScholarship())) {
            setValuesToPreparedStatement(scholarship, preparedStatement);
            preparedStatement.executeUpdate();
        }
    }

    private void setValuesToPreparedStatement(Scholarship scholarship, java.sql.PreparedStatement preparedStatement) {
        final List<Object> fieldValues = scholarship.getFieldValues();
        fieldValues.stream()
                .filter(field -> fieldValues.indexOf(field) != ZERO)
                .forEach(field -> {
                    try {
                        preparedStatement.setObject(fieldValues.indexOf(field) + ONE, field);
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                });
    }

}
