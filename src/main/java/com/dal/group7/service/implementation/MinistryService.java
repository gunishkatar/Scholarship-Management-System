package com.dal.group7.service.implementation;

import com.dal.group7.persistent.implementations.ScholarshipDao;
import com.dal.group7.persistent.model.Scholarship;
import org.json.JSONObject;

import java.sql.SQLException;

public class MinistryService {
    private final ScholarshipDao scholarshipDao;
    private final JsonFileReader jsonFileReader;

    public MinistryService(ScholarshipDao scholarshipDao, JsonFileReader jsonFileReader) {
        this.scholarshipDao = scholarshipDao;
        this.jsonFileReader= jsonFileReader;
    }

    public void saveScholarship(String filePath) throws SQLException {
        final JSONObject jsonObject = jsonFileReader.readJson(filePath);
        Scholarship scholarship = new Scholarship().from(jsonObject);
        scholarshipDao.insertOne(scholarship);
    }
}
