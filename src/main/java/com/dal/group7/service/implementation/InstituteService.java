package com.dal.group7.service.implementation;

import com.dal.group7.persistent.implementations.InstituteDao;
import com.dal.group7.persistent.model.Institute;
import com.dal.group7.service.interfaces.UserService;
import org.json.JSONObject;
import java.sql.SQLException;


public class InstituteService implements UserService {
    private final InstituteDao instituteDao;
    private final JsonFileReader jsonFileReader;

    public InstituteService(JsonFileReader jsonFileReader, InstituteDao instituteDao) {
        this.jsonFileReader = jsonFileReader;
        this.instituteDao = instituteDao;
    }


    @Override
    public void signup(String filepath)throws SQLException {
        final JSONObject jsonObject = jsonFileReader.readJson(filepath);
        Institute institute = new Institute().from(jsonObject);
        instituteDao.insertOne(institute);
    }


    @Override
    public void login() {




    }

    @Override
    public void logout() {

    }
}
