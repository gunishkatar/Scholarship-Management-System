package com.dal.group7.shared;

import com.dal.group7.persistent.implementations.PwdEncryptDao;

import java.sql.*;
import java.util.HashMap;

public class PwdEncrypt {
    private final PwdEncryptDao pwdDao;

    public PwdEncrypt(PwdEncryptDao pwdDao) {
        this.pwdDao = pwdDao;
    }

    public String getEncryptedPwd(String inputPwd) {
        String encryptedPwd ="";
        try {
            HashMap<String, String> hmap = pwdDao.getValue();
            encryptedPwd = getStringHash(inputPwd, hmap);
        } catch (SQLException e) {
            System.out.println("Error in encryption_logic query" + e);
        }
        return encryptedPwd;
    }

    public String getStringHash(String inputPwd, HashMap<String, String> hmap) {
        String hashPwd = "";
        String[] hashPwdArr = inputPwd.split("");
        for (int i = 0; i < inputPwd.length(); i++) {
            if(hmap.containsKey(hashPwdArr[i])) {
                hashPwd += hmap.get(hashPwdArr[i]);
            } else {
                hashPwd+= hashPwdArr[i];
            }
        }
        return hashPwd;
    }
}
