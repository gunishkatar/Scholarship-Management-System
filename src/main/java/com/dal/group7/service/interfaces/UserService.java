package com.dal.group7.service.interfaces;


import java.sql.SQLException;

/**
 * <h1>UserService Interface</h1>
 * The UserService Interface contains abstract methods that a user needs to implement
 *
 * @author : Sai Rahul Kodumuru
 * @version : 1.0
 * @since : 2021-July-05
 */
public interface UserService {

    void signup(String filename) throws SQLException;

    void login();

    void logout();
}
