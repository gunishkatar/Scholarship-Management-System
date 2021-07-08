package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.model.Institute;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class InstituteDaoTest {

    private static final Institute INSTITUTE = new Institute(1, "Institute");
    private static final String INSTITUTE_NAME = "Institute";
    private static final int ID = 1;
    private static final int ID_2 = 2;
    private static final String INSTITUTE_NAME_2 = "Institute2";
    private final ConnectionManager connectionManagerMock = Mockito.mock(ConnectionManager.class);
    private final Connection connection = Mockito.mock(Connection.class);
    private final PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    private final ResultSet resultSet = Mockito.mock(ResultSet.class);
    private final InstituteDao instituteDao = new InstituteDao(connectionManagerMock);

    @Test
    void get_returnInstitute_whenIdIsPresent() throws SQLException {
        setUpMock();
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getInt(any())).thenReturn(ID);
        Mockito.when(resultSet.getString(any())).thenReturn(INSTITUTE_NAME);

        final Optional<Institute> institute = instituteDao.get(1);

        assertTrue(institute.isPresent());
        assertEquals(INSTITUTE.getId(), institute.get().getId());
        assertEquals(INSTITUTE.getName(), institute.get().getName());
    }

    @Test
    void get_returnEmpty_whenIdIsNotPresent() throws SQLException {
        setUpMock();
        Mockito.when(resultSet.next()).thenReturn(false);

        final Optional<Institute> institute = instituteDao.get(ID);

        assertFalse(institute.isPresent());
    }

    @Test
    void getAll_returnInstitutes_whenInstitutesArePresent() throws SQLException {
        setUpMock();
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(any())).thenReturn(ID).thenReturn(ID_2);
        Mockito.when(resultSet.getString(any())).thenReturn(INSTITUTE_NAME).thenReturn(INSTITUTE_NAME_2);

        final List<Institute> institute = instituteDao.getAll();
        final List<Institute> expected = asList(new Institute(ID, INSTITUTE_NAME), new Institute(ID_2, INSTITUTE_NAME_2));

        assertFalse(institute.isEmpty());
        assertEquals(expected.get(0).getId(), institute.get(0).getId());
        assertEquals(expected.get(0).getName(), institute.get(0).getName());
        assertEquals(expected.get(1).getId(), institute.get(1).getId());
        assertEquals(expected.get(1).getName(), institute.get(1).getName());
    }

    @Test
    void getAll_returnEmptyList_whenInstitutesAreNotPresent() throws SQLException {
        setUpMock();
        Mockito.when(resultSet.next()).thenReturn(false);

        final List<Institute> institutes = instituteDao.getAll();

        assertTrue(institutes.isEmpty());
    }

    private void setUpMock() throws SQLException {
        Mockito.when(connectionManagerMock.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }
}