package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Scholarship;

import java.sql.SQLException;

import static com.dal.group7.constants.SQLConstants.insertIntoTableAllFields;

public class ScholarshipDao extends Dao<Integer, Scholarship> {

    private static final String SCHOLARSHIP = "SCHOLARSHIP";
    private ConnectionManager connectionManager;

    public ScholarshipDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void insertOne(Scholarship scholarship) throws SQLException {
        try(var connection = connectionManager.getConnection();
            var preparedStatement = connection.prepareStatement(insertIntoTableAllFields(SCHOLARSHIP, 7))) {
            preparedStatement.setInt(1, scholarship.getId());
            preparedStatement.setString(2, scholarship.getScholarShipName());
            preparedStatement.setDate(3, scholarship.getEffectiveDate());
            preparedStatement.setDouble(4, scholarship.getScholarshipAmount());
            preparedStatement.setBoolean(5, scholarship.getCriteriaGirlChild());
            preparedStatement.setBoolean(6, scholarship.getCriteriaAcademics());
            preparedStatement.setBoolean(7, scholarship.getCriteriaSports());
            preparedStatement.executeUpdate();
        }
    }

}
