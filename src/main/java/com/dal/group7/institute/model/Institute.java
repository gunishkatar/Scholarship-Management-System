package com.dal.group7.institute.model;


import java.sql.ResultSet;
import java.sql.SQLException;

import static com.dal.group7.institute.constants.InstituteConstants.ID;
import static com.dal.group7.institute.constants.InstituteConstants.NAME;

public class Institute {
    private Integer id;
    private String name;

    public Institute() {
    }

    public Institute(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Institute from(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(ID);
        name = resultSet.getString(NAME);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
