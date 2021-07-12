package com.dal.group7.service.implementation;

import com.dal.group7.persistent.implementations.ConnectionManager;
import com.dal.group7.persistent.implementations.InstituteDao;
import com.dal.group7.persistent.model.Institute;
import com.dal.group7.service.interfaces.UserService;
import com.dal.group7.shared.PwdEncrypt;
import org.json.JSONObject;
import java.sql.SQLException;


public class InstituteService implements UserService {
    private final InstituteDao instituteDao;
    private final JsonFileReader jsonFileReader;
    private PwdEncrypt passwordClass;
    private ConnectionManager connectionManager;

    public InstituteService(JsonFileReader jsonFileReader, InstituteDao instituteDao, PwdEncrypt passwordClass, ConnectionManager connectionManager) {
        this.jsonFileReader = jsonFileReader;
        this.instituteDao = instituteDao;
        this.passwordClass = passwordClass;
        this.connectionManager = connectionManager;
    }


    @Override
    public void signup(String filepath)throws SQLException {
        final JSONObject jsonObject = jsonFileReader.readJson(filepath);
        Institute institute = new Institute().from(jsonObject);
        instituteDao.insertOne(institute);
    }


    @Override
    public void login() throws SQLException {


        InstituteLoginService instituteLoginService = new InstituteLoginService(this.passwordClass, this.connectionManager);
        if(instituteLoginService.instituteLogin()){
            System.out.println("Re-direct to InstituteView");
        }else{
            System.out.println("Throw Error");
        }


    }

    @Override
    public void logout() {

    }
}
