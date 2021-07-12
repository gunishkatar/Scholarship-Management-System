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

    public boolean isValid(Institute institute){
        boolean idFlag = false;
        boolean nameFlag = false;
        boolean emailIdFlag = false;
        boolean registrationCodeFlag = false;
        boolean phoneNumberFlag = false;
        boolean addressFlag = false;
        boolean stateFlag = false;
        boolean cityFlag = false;
        boolean countryFlag = false;
        boolean pinCodeFlag = false;

        idFlag = (institute.getId()>0);
        nameFlag = (!institute.getName().equals("")) && (institute.getName() != null);
        emailIdFlag = (!institute.getEmailId().equals("")) && (institute.getEmailId() != null);
        registrationCodeFlag = (institute.getRegistrationCode()>0);
        phoneNumberFlag = (institute.getPhoneNumber()>0);
        addressFlag = (!institute.getAddress().equals("")) && (institute.getAddress() != null);
        stateFlag = (!institute.getState().equals("")) && (institute.getState() != null);
        cityFlag = (!institute.getCity().equals("")) && (institute.getCity() != null);
        countryFlag = (!institute.getCountry().equals("")) && (institute.getCountry() != null);
        pinCodeFlag = (institute.getPinCode()>0);
        return (idFlag && nameFlag && emailIdFlag && registrationCodeFlag && phoneNumberFlag && addressFlag && stateFlag && cityFlag && countryFlag && pinCodeFlag);
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
