package com.dal.group7.persistent.implementations;

import com.dal.group7.constants.SQLConstants;
import com.dal.group7.constants.TableConstants;
import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Student;
import com.dal.group7.shared.PwdEncrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h1>StudentDao is an extension of the Dao class</h1>
 * <p>It has the methods to interact with student DB tables</p>
 *
 * @author : Sai Rahul Kodumuru
 * @version : 1.0
 * @since : 2021-July-05
 */
public class StudentDao extends Dao<Integer, Student> {

    public Boolean doesEmailExist(String emailId) throws SQLException {

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(SQLConstants.getSelectByUserIdQuery(TableConstants.USER_CREDENTIAL))) {

            preparedStatement.setString(1, emailId);
            ResultSet rs = preparedStatement.executeQuery();

            return rs.next();
        }

    }

    public void insertOne(Student student) {

        try (Connection connection = DatabaseManager.getConnection()) {
            PwdEncrypt pwdEncrypt = new PwdEncrypt(new PwdEncryptDao(new ConnectionManager()));

            PreparedStatement statement;
            statement = connection.prepareStatement(SQLConstants.getInsertNewStudent());

            // student_basic table object
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmailId());
            statement.setString(4, student.getPhoneNumber());
            statement.setString(5, student.getPassportNumber());
            statement.setString(6, student.getDob());
            statement.setString(7, student.getGender());
            statement.setString(8, student.getState());
            statement.setString(9, student.getCity());
            statement.setString(10, student.getPincode());
            statement.setString(11, student.getCountry());

            statement.execute();

            statement = connection.prepareStatement(SQLConstants.getInsertNewUser());
            statement.setString(1, student.getEmailId());
            statement.setString(2, pwdEncrypt.getEncryptedPwd(student.getPassword()));
            statement.setString(3, "1");
            statement.setString(4, student.getSecurityAnswerOne());
            statement.setString(5, student.getSecurityAnswerTwo());
            statement.setString(6, student.getSecurityAnswerThree());
            statement.setString(7, student.getClass().getSimpleName().toLowerCase());

            statement.execute();

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
