package com.dal.group7.service.implementation;

import com.dal.group7.persistent.implementations.ConnectionManager;
import com.dal.group7.persistent.implementations.UserCredentialDao;
import com.dal.group7.persistent.model.UserCredential;
import com.dal.group7.shared.PwdEncrypt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class InstituteLoginService {

    private String userId;
    private String password;
    private PwdEncrypt passwordClass;
    private Optional<UserCredential> userCredential;
    private UserCredentialDao userCredentialDao;
    private ConnectionManager connectionManager;

    public InstituteLoginService(PwdEncrypt passwordClass, ConnectionManager connectionManager) {
        this.passwordClass = passwordClass;
        this.connectionManager = connectionManager;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void credentialFromUser(){
        Scanner scn  =new Scanner(System.in);
        System.out.println("Enter the valid User ID:\n");
        setUserId(scn.nextLine());
        System.out.println("Enter the valid Password:\n");
        setPassword(scn.nextLine());
    }

    public void encryptedPassword(){
        setPassword(passwordClass.getEncryptedPwd(getPassword()));
    }

    public boolean getStoredCredential() throws SQLException {
        userCredentialDao = new UserCredentialDao(connectionManager);
        if(userCredentialDao.doesUserExist(getUserId())){
            userCredential = userCredentialDao.get(getUserId());
            return true;
        }
        return false;

    }

    public boolean areCredentialsvalid(){
        if(getPassword().equals(userCredential.get().getPassword())){
            return true;
        }
        return false;
    }




    public boolean instituteLogin() throws SQLException {
        credentialFromUser();
        encryptedPassword();
        if(getStoredCredential()){
            return areCredentialsvalid();
        }

        return false;

    }




}
