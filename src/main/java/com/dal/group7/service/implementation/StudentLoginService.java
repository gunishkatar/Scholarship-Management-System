package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.UserCredential;
import com.dal.group7.shared.PwdEncrypt;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.dal.group7.constants.FieldConstants.ONE;
import static com.dal.group7.constants.FieldConstants.ZERO;
import static com.dal.group7.constants.ViewConstants.*;

public class StudentLoginService {

  private Dao<String, UserCredential> userCredentialDao;
  private PwdEncrypt pwdEncrypt;
  private UserCredential userCredential;
  private String userId;
  private String password;
  private int failLoginCount;
  private static final String YES = "yes";

  public StudentLoginService(Dao<String, UserCredential> userCredentialDao, PwdEncrypt pwdEncrypt) {
    this.userCredentialDao = userCredentialDao;
    this.pwdEncrypt = pwdEncrypt;
  }

  public UserCredential userLogin(String userId, String password) throws SQLException {
    this.userId = userId;
    this.password = getEncryptedPassword(password);

    if (getStoredCredential() && areCredentialsValid() && !isStudentSoftBlocked() && !isStudentHardBlocked()
            && !isStudentBlackListed()) {
      userCredentialDao.updateLastLoginTime(userId);
      updateFailLoginCountOnSuccess();
      return userCredential;
    }
    if(!getStoredCredential()) {
      throw new IllegalArgumentException(NO_USER_FOUND);
    }
    else if (isStudentBlackListed()) {
      throw new IllegalArgumentException(STUDENT_BANNED_MSG);
    } else if (isStudentHardBlocked()) {
      throw new IllegalArgumentException(STUDENT_HARD_BLOCK_MSG);
    } else if (isStudentSoftBlocked()) {
      //TO DO
      throw new IllegalArgumentException(STUDENT_SOFT_BLOCK_MSG);
    } else {
      updateFailLoginCount();
      throw new IllegalArgumentException(INVALID_CREDENTIALS);
    }
  }

  private void updateFailLoginCount() throws SQLException {
    this.failLoginCount = getLoginCount(userCredential.getFailedLoginCount());
    userCredentialDao.updateValue(userId, "failed_login_count", failLoginCount + ONE);
  }

  private void updateFailLoginCountOnSuccess() throws SQLException {
    this.failLoginCount = getLoginCount(userCredential.getFailedLoginCount());
    userCredentialDao.updateValue(userId, "failed_login_count", ZERO);
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
    return userCredential.getIsSoftBlock().equals(YES);
  }

  private boolean isStudentHardBlocked() {
    return userCredential.getIsHardBlock().equals(YES);
  }

  private boolean isStudentBlackListed() {
    return userCredential.getIsBlackListed().equals(YES);
  }

  public Integer getLoginCount(String loginCount) {
    return Integer.parseInt(loginCount);
  }
}
