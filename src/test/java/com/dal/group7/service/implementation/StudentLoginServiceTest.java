package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Student;
import com.dal.group7.persistent.model.UserCredential;
import com.dal.group7.shared.PwdEncrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class StudentLoginServiceTest {

  private static final String PASSWORD = "pwd";
  private static final String USERNAME = "user";
  private static final UserCredential CREDENTIALS = new UserCredential(USERNAME, PASSWORD, "NO", "NO",
          "2020-01-10", "1", "NONE", "NONE",
          "NONE", "0", "student", "NO");
  @Mock
  private Dao<String, UserCredential> userCredentialDao;

  @Mock
  private PwdEncrypt pwdEncrypt;

  @InjectMocks
  private StudentLoginService studentLoginService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnCredentialsIfUsernameAndPasswordAreValid() throws SQLException {
    Mockito.when(pwdEncrypt.getEncryptedPwd(any())).thenReturn(PASSWORD);
    Mockito.when(userCredentialDao.doesExist(any())).thenReturn(true);
    Mockito.when(userCredentialDao.get(any())).thenReturn(Optional.of(CREDENTIALS));

    final UserCredential userCredential = studentLoginService.userLogin(USERNAME, PASSWORD);

    assertEquals(CREDENTIALS, userCredential);
  }

  @Test
  void shouldThrowExceptionIfUsernameDoesntExist() throws SQLException {
    Mockito.when(pwdEncrypt.getEncryptedPwd(any())).thenReturn(PASSWORD);
    Mockito.when(userCredentialDao.doesExist(any())).thenReturn(false);

    final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> studentLoginService.userLogin(USERNAME, PASSWORD));

    assertEquals("You have entered invalid Credentials", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionIfNoCredentialsReturned() throws SQLException {
    Mockito.when(pwdEncrypt.getEncryptedPwd(any())).thenReturn(PASSWORD);
    Mockito.when(userCredentialDao.doesExist(any())).thenReturn(true);
    Mockito.when(userCredentialDao.get(any())).thenReturn(Optional.empty());

    final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> studentLoginService.userLogin(USERNAME, PASSWORD));

    assertEquals("You have entered invalid Credentials", exception.getMessage());
  }
}