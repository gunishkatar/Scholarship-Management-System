package com.dal.group7.institute.db;

import com.dal.group7.institute.model.Institute;
import com.dal.group7.shared.db.ConnectionManager;
import com.dal.group7.shared.db.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dal.group7.institute.constants.InstituteConstants.PARAMETER_INDEX_ID;
import static com.dal.group7.institute.constants.InstituteSQLConstants.selectAllQuery;
import static com.dal.group7.institute.constants.InstituteSQLConstants.selectByIdQuery;

public class InstituteDao implements Dao<Integer, Institute> {
    private final ConnectionManager connectionManager;

    public InstituteDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Optional<Institute> get(Integer id) throws SQLException {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectByIdQuery)) {
            preparedStatement.setInt(PARAMETER_INDEX_ID, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? Optional.of(new Institute().from(resultSet)) : Optional.empty();
        }
    }

    @Override
    public List<Institute> getAll() throws SQLException {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllQuery)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            List<Institute> institutes = new ArrayList<>();
            while (resultSet.next()) {
                institutes.add(new Institute().from(resultSet));
            }
            return institutes;
        }
    }
}
