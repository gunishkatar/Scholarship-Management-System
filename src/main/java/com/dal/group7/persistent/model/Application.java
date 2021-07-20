package com.dal.group7.persistent.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Application {
    String applicationId;
    int schemeId;
    String studentId;
    int instituteId;
    String appliedDate;
    String lastUpdate;
    String applicationStatus;
    String instituteStatus;
    String ministryStatus;
    double academicScore;
    double nonAcademicScore;
    double profileScore;
    Scheme scheme;

    public String getApplicationId() {
        return applicationId;
    }

    public int getSchemeId() {
        return schemeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getInstituteId() {
        return instituteId;
    }

    public String getAppliedDate() {
        return appliedDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public String getInstituteStatus() {
        return instituteStatus;
    }

    public String getMinistryStatus() {
        return ministryStatus;
    }

    public double getAcademicScore() {
        return academicScore;
    }

    public void setAcademicScore(double academicScore) {
        this.academicScore = academicScore;
    }

    public double getNonAcademicScore() {
        return nonAcademicScore;
    }

    public void setNonAcademicScore(double nonAcademicScore) {
        this.nonAcademicScore = nonAcademicScore;
    }

    public double getProfileScore() {
        return profileScore;
    }

    public void setProfileScore(double profileScore) {
        this.profileScore = profileScore;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public Application from(ResultSet resultSet) throws SQLException {
        int counter = 1;
        this.applicationId = resultSet.getString(counter++);
        this.schemeId = resultSet.getInt(counter++);
        this.studentId = resultSet.getString(counter++);
        this.instituteId = resultSet.getInt(counter++);
        this.appliedDate = resultSet.getString(counter++);
        this.lastUpdate = resultSet.getString(counter++);
        this.applicationStatus = resultSet.getString(counter++);
        this.instituteStatus = resultSet.getString(counter++);
        this.ministryStatus = resultSet.getString(counter++);
        this.academicScore = resultSet.getDouble(counter++);
        this.nonAcademicScore = resultSet.getDouble(counter++);
        this.profileScore = resultSet.getDouble(counter);
        return this;
    }
}
