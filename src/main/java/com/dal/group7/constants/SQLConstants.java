package com.dal.group7.constants;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SQLConstants {
    private static final String SELECT_ALL_QUERY = "select * from ";
    private static final String WHERE_ID = " where id = ?";
    private static final String WHERE_USER_ID = " where user_id = ?";
    private static final String INSERT_NEW_STUDENT = "INSERT INTO student_basic (first_name, last_name, email_id, " +
            "phone_number, passport_number, dob, gender, state, city, pincode, country) " +
            "values (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_NEW_USER = "INSERT INTO user_credential (user_id, password, " +
            "security_id, security_answer_one, security_answer_two, security_answer_three, " +
            "role_type) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_NEW_INSTITUTE = "INSERT INTO institute_basic (institute_name, email_id, registration_code, phone_number," +
            "address, state, city, country, pinCode)" +
            "VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_NEW_SCHOLARSHIP = "INSERT INTO scholarship (name, effectiveDate, scholarship_amount," +
            " criteria_girl, criteria_academic, criteria_sports)" +
            "VALUES(?,?,?,?,?,?)";


    private SQLConstants() {
    }

    public static String getSelectAllQuery(String table) {
        return SELECT_ALL_QUERY + table;
    }

    public static String getSelectByIdQuery(String table) {
        return SELECT_ALL_QUERY + table + WHERE_ID;
    }

    public static String getSelectByUserIdQuery(String table) {
        return SELECT_ALL_QUERY + table + WHERE_USER_ID;
    }

    public static String getInsertNewScholarship() {
        return INSERT_NEW_SCHOLARSHIP;
    }

    public static String insertIntoTableAllFields(String table, Integer numberOfFields) {
        final String params = IntStream.range(0, numberOfFields).mapToObj(i -> "?").collect(Collectors.joining(","));
        return "insert into " + table + " values(" + params + ");";
    }

    public static String getInsertNewStudent() {
        return INSERT_NEW_STUDENT;
    }


    public static String getInsertNewUser() {
        return INSERT_NEW_USER;
    }
}
