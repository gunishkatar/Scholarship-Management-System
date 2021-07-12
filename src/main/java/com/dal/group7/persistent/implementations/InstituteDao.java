package com.dal.group7.persistent.implementations;
import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Institute;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dal.group7.constants.FieldConstants.PARAMETER_INDEX_ID;
import static com.dal.group7.constants.SQLConstants.*;

public class InstituteDao extends Dao<Integer, Institute> {

    private static final String INSTITUTE = "INSTITUTE";
    private final ConnectionManager connectionManager;

    public InstituteDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }



    public void insertOne(Institute institute) throws SQLException{
        try (var connection = connectionManager.getConnection();
             //var preparedStatement = connection.prepareStatement(SQLConstants.getInsertNewInstitute())){
            var preparedStatement = connection.prepareStatement(insertIntoTableAllFields(INSTITUTE, 10))) {
            preparedStatement.setInt(1, institute.getId());
            preparedStatement.setString(2, institute.getName());
            preparedStatement.setString(3, institute.getEmailId());
            preparedStatement.setInt(4, institute.getRegistrationCode());
            preparedStatement.setInt(5, institute.getPhoneNumber());
            preparedStatement.setString(6, institute.getAddress());
            preparedStatement.setString(7, institute.getState());
            preparedStatement.setString(8, institute.getCity());
            preparedStatement.setString(9, institute.getCountry());
            preparedStatement.setInt(10, institute.getPinCode());
            preparedStatement.executeUpdate();
        }
    }

    public Optional<Institute> get(Integer id) throws SQLException {
        try(var connection = connectionManager.getConnection();
            var preparedStatement = connection.prepareStatement(getSelectByIdQuery(INSTITUTE))) {
            preparedStatement.setInt(PARAMETER_INDEX_ID, id);
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? Optional.of(new Institute().from(resultSet)) : Optional.empty();
        }
    }


    public List<Institute> getAll() throws SQLException {
        try(var connection = connectionManager.getConnection();
            var preparedStatement = connection.prepareStatement(getSelectAllQuery(INSTITUTE))) {
            final var resultSet = preparedStatement.executeQuery();
            List<Institute> institutes = new ArrayList<>();
            while (resultSet.next()) {
                institutes.add(new Institute().from(resultSet));
            }
            return institutes;
        }
    }
}
