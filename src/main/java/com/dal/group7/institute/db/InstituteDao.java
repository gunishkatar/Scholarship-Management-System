package com.dal.group7.institute.db;

import com.dal.group7.institute.model.Institute;
import com.dal.group7.shared.db.ConnectionManager;
import com.dal.group7.shared.db.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InstituteDao implements Dao<Integer, Institute> {

    @Override
    public Optional<Institute> get(Integer id) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getSql(id))) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Optional.of(new Institute().from(resultSet));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Institute> getAll() {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAllSql())) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            List<Institute> institutes = new ArrayList<>();
            while (resultSet.next()) {
                institutes.add(new Institute().from(resultSet));
            }
            return institutes;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Collections.emptyList();
    }

    private String getAllSql() {
        return "select * from INSTITUTE";
    }

    private String getSql(Integer id) {
        return "select * from INSTITUTE where id =\"" + id + "\"";
    }
}
