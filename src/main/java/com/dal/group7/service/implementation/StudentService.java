package com.dal.group7.service.implementation;

import com.dal.group7.persistent.implementations.StudentDao;
import com.dal.group7.persistent.model.Student;
import com.dal.group7.service.interfaces.UserService;
import org.json.JSONObject;

import java.sql.SQLException;

/**
 * <h1>StudentService is an implementation of the UserService Interface</h1>
 *
 * @author : Sai Rahul Kodumuru
 * @version : 1.0
 * @since : 2021-July-05
 */
public class StudentService implements UserService {
    private final StudentDao studentDao;
    private final JsonFileReader jsonFileReader;

    public StudentService() {
        this.studentDao = new StudentDao();
        this.jsonFileReader = new JsonFileReader();
    }

    @Override
    public void signup(String filepath) {

        try {
            final JSONObject jsonObject = jsonFileReader.readJson(filepath);
            Student student = new Student().from(jsonObject);

            if (isValid(student) && !isStudentExists(student.getEmailId())) {
                System.out.println("Inserting Student....");
                studentDao.insertOne(student);
            } else {
                System.out.println("Unable to save the user...please try again");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean isStudentExists(String emailId) throws SQLException {
        return studentDao.doesEmailExist(emailId);
    }

    public Boolean isValid(Student student) {
        boolean names = !student.getFirstName().equals("") && student.getFirstName() != null
                && !student.getLastName().equals("") && student.getLastName() != null;
        boolean passwords = student.getPassword().equals(student.getReConfirmPassword())
                && !student.getPassword().equals("") && student.getPassword() != null;
        boolean emailID = student.getEmailId() != null && !student.getEmailId().equals("");
        boolean cities = student.getCity() != null && !student.getCity().equals("");
        boolean dob = student.getDob() != null && !student.getDob().equals("");
        boolean gender = student.getGender() != null && !student.getGender().equals("");
        boolean pincode = student.getPincode() != null && !student.getPincode().equals("");
        boolean address = student.getState() != null && !student.getState().equals("")
                && student.getCountry() != null && !student.getCountry().equals("");
        boolean securityAnswers = student.getSecurityAnswerOne() != null && !student.getSecurityAnswerOne().equals("")
                && student.getSecurityAnswerTwo() != null && !student.getSecurityAnswerTwo().equals("")
                && student.getSecurityAnswerThree() != null && !student.getSecurityAnswerThree().equals("");

        return (names && passwords && emailID && cities && dob && gender && pincode && address && securityAnswers);
    }


    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }
}
