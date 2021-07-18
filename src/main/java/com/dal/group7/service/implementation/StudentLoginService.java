package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.UserCredential;
import com.dal.group7.shared.PwdEncrypt;

import java.sql.SQLException;

import static com.dal.group7.constants.ViewConstants.*;

public class StudentLoginService {

  private Dao<String, UserCredential> userCredentialDao;
  private PwdEncrypt pwdEncrypt;
  private UserCredential userCredential;
  private String userId;
  private String password;

  public StudentLoginService(Dao<String, UserCredential> userCredentialDao, PwdEncrypt pwdEncrypt) {
    this.userCredentialDao = userCredentialDao;
    this.pwdEncrypt = pwdEncrypt;
  }

  public UserCredential userLogin(String userId, String password) throws SQLException {
    this.userId = userId;
    this.password = getEncryptedPassword(password);

    if (getStoredCredential() && areCredentialsValid()) {
      if (isStudentSoftBlocked()) {
        //TO DO
        throw new IllegalArgumentException(STUDENT_SOFT_BLOCK_MSG);
      } else if (isStudentHardBlocked()) {
        //TO DO
        throw new IllegalArgumentException(STUDENT_HARD_BLOCK_MSG);
      } else if (isStudentBlackListed()) {
        throw new IllegalArgumentException(STUDENT_BANNED_MSG);
      } else {
        return userCredential;
      }
    }
    throw new IllegalArgumentException(INVALID_CREDENTIALS);
  }

  private String getEncryptedPassword(String password) {
    return pwdEncrypt.getEncryptedPwd(password);
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

  private boolean isStudentSoftBlocked() {
    return userCredential.getIsSoftBlock().equals("yes");
  }

  private boolean isStudentHardBlocked() {
    return userCredential.getIsHardBlock().equals("yes");
  }

  private boolean isStudentBlackListed() {
    return userCredential.getIsBlackListed().equals("yes");
  }
}
