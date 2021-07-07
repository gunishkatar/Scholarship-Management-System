package com.dal.group7.institute.db;

import com.dal.group7.institute.model.Institute;
import com.dal.group7.shared.db.ConnectionManager;
import com.dal.group7.shared.db.Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dal.group7.institute.constants.InstituteConstants.PARAMETER_INDEX_ID;
import static com.dal.group7.institute.constants.InstituteSQLConstants.SELECT_ALL_QUERY;
import static com.dal.group7.institute.constants.InstituteSQLConstants.SELECT_BY_ID_QUERY;

public class InstituteDao implements Dao<Integer, Institute> {
    private final ConnectionManager connectionManager;

    public InstituteDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Optional<Institute> get(Integer id) throws SQLException {
        try(var connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(PARAMETER_INDEX_ID, id);
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? Optional.of(new Institute().from(resultSet)) : Optional.empty();
        }
    }

    @Override
    public List<Institute> getAll() throws SQLException {
        try(var connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            final var resultSet = preparedStatement.executeQuery();
            List<Institute> institutes = new ArrayList<>();
            while (resultSet.next()) {
                institutes.add(new Institute().from(resultSet));
            }
            return institutes;
        }
    }
}
