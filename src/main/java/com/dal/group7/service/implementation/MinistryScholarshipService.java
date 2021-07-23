package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Scholarship;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MinistryScholarshipService {
    private final Dao<Integer, Scholarship> scholarshipDao;
    private final JsonFileReader jsonFileReader;

    public MinistryScholarshipService(Dao<Integer, Scholarship> scholarshipDao,
                                      JsonFileReader jsonFileReader) {
        this.scholarshipDao = scholarshipDao;
        this.jsonFileReader = jsonFileReader;
    }

    public void saveScholarship(String filePath)
            throws SQLException, IOException {
        var jsonObject = jsonFileReader.readJson(filePath);
        var scholarship = new Scholarship().from(jsonObject);
        scholarshipDao.insertOne(scholarship);
    }

    public List<Scholarship> displayScholarships() throws SQLException {
        return scholarshipDao.getAll();
    }
}
