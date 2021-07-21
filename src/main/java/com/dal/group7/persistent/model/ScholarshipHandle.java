package com.dal.group7.persistent.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScholarshipHandle extends Scholarship {

    public Scholarship formResultSet(ResultSet resultSet) throws
            SQLException {
        this.scholarShipName = resultSet.getString(2);
        this.effectiveDate = resultSet.getDate(3);
        this.scholarshipAmount = resultSet.getLong(4);
        this.criteriaGirlChild = resultSet.getBoolean(5);
        this.criteriaAcademics = resultSet.getBoolean(6);
        this.criteriaSports = resultSet.getBoolean(7);
        return this;
    }

}
