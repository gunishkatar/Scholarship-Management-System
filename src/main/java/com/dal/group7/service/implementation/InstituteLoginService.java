package com.dal.group7.service.implementation;

import com.dal.group7.persistent.implementations.UserCredentialDao;
import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.UserCredential;
import com.dal.group7.shared.PwdEncrypt;

import java.sql.SQLException;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.constants.ViewConstants.INVALID_CREDENTIALS;

public class InstituteLoginService {

    private UserCredentialDao userCredentialDao;
    private String userId;
    private String password;
    private PwdEncrypt passwordClass;
    private UserCredential userCredential;
    private static final String YES = "yes";

    public InstituteLoginService(UserCredentialDao userCredentialDao, PwdEncrypt pwdEncrypt) {
        this.userCredentialDao = userCredentialDao;
        this.passwordClass = pwdEncrypt;
    }

    private String getEncryptedPassword(String password) {
        return passwordClass.getEncryptedPwd(password);
    }

    private boolean getStoredCredential() throws SQLException {
        if (userCredentialDao.doesExist(userId)) {
            userCredential = userCredentialDao.get(userId)
                    .orElseThrow(() -> new IllegalArgumentException(INVALID_CREDENTIALS));
            return true;
        }
        return false;

    }

    private boolean areCredentialsValid() {
        return password.equals(userCredential.getPassword());
    }

}
