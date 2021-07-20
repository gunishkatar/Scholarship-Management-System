package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Application;

import java.sql.SQLException;

import static com.dal.group7.persistent.model.ApplicationStatus.*;

public class InstituteApplicationService {
    private static final String INSTITUTE_STATUS = "institute_status";
    private final Dao<String, Application> applicationDao;

    public InstituteApplicationService(Dao<String, Application> applicationDao) {
        this.applicationDao = applicationDao;
    }

    public boolean doesExist(String applicationNumber) throws SQLException {
        return applicationDao.doesExist(applicationNumber);
    }

    public void approveApplication(String applicationNumber) throws SQLException {
        applicationDao.updateValue(applicationNumber, INSTITUTE_STATUS, APPROVED.toString());
    }

    public void rejectApplication(String applicationNumber) throws SQLException {
        applicationDao.updateValue(applicationNumber, INSTITUTE_STATUS, REJECTED.toString());
    }
}