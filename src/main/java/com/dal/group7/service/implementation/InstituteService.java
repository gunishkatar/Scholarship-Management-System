package com.dal.group7.service.implementation;

import com.dal.group7.constants.InstituteConstants;
import com.dal.group7.persistent.implementations.ConnectionManager;
import com.dal.group7.persistent.implementations.InstituteDao;
import com.dal.group7.persistent.implementations.PwdEncryptDao;
import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Institute;
import com.dal.group7.service.interfaces.UserService;
import com.dal.group7.shared.PwdEncrypt;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;


public class InstituteService implements UserService {
    private final Dao<Integer, Institute> instituteDao;
    private final JsonFileReader jsonFileReader;
    private PwdEncrypt passwordClass;
    private ConnectionManager connectionManager;

    public InstituteService(Dao<Integer, Institute> instituteDao,
                            JsonFileReader jsonFileReader) {
        this.instituteDao = instituteDao;
        this.jsonFileReader = jsonFileReader;

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

    public Boolean isValidInstituteEmail(String emailId) {
        try {
            if (!emailId.equals("")) {
                String[] emailSplits =
                        emailId.split(InstituteConstants.getInstEmailDelimiter());

                if (emailSplits.length > 0) {
                    String userDomain = emailSplits[1];
                    return (!InstituteConstants.getInvalidDomains()
                            .contains(userDomain));
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Email");
        }
        return false;
    }

    @Override
    public void signup(String filepath) throws SQLException, IOException {
        final JSONObject jsonObject = jsonFileReader.readJson(filepath);
        Institute institute = new Institute().from(jsonObject);
        instituteDao.insertOne(institute);
        if (Boolean.TRUE.equals(isValid(institute)) &&
                Boolean.FALSE
                        .equals(doesInstituteExist(institute.getEmailId())) &&
                Boolean.TRUE
                        .equals(isValidInstituteEmail(institute.getEmailId()))) {
            instituteDao.insertOne(institute);
        } else {
            throw new IllegalArgumentException(
                    "Invalid institute parameters passed");
        }
    }



    public Boolean doesInstituteExist(String emailId) throws SQLException {
        return instituteDao.doesEmailExist(emailId);
    }



    @Override
    public void login() throws SQLException {


//        InstituteLoginService instituteLoginService = new InstituteLoginService(this.passwordClass, this.connectionManager);
//        if(instituteLoginService.instituteLogin()){
//            System.out.println("Re-direct to InstituteView");
//        }else{
//            System.out.println("Throw Error");
//        }


    }

    @Override
    public void logout() {

    }
}
