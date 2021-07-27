package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.UserCredential;

import java.sql.SQLException;

public class MinistryLoginService {
    private Dao<String, UserCredential> userCredentialDao;
    private PwdEncrypt pwdEncrypt;
    private UserCredential userCredential;
    private String userId;
    private String password;

    public MinistryLoginService(Dao<String, UserCredential> userCredentialDao, PwdEncrypt pwdEncrypt) {
        this.userCredentialDao = userCredentialDao;
        this.pwdEncrypt = pwdEncrypt;
    }

    public UserCredential userLogin(String userId, String password) throws SQLException {
        this.userId =  userId;
        this.password = getEncryptedPassword(password);
        if(getStoredCredential() && areCredentialsValid()){
            return userCredential;
        }
        throw new IllegalArgumentException("Invalid Credentials");
    }

    private String getEncryptedPassword(String password){
        return pwdEncrypt.getEncryptedPwd(password);
    }

    private boolean getStoredCredential() throws SQLException {
        if(userCredentialDao.doesExist(userId)){
            userCredential = userCredentialDao.get(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Credentials"));
            return true;
        }
        return false;

    }

    private boolean areCredentialsValid(){
        return password.equals(userCredential.getPassword());
    }
}
