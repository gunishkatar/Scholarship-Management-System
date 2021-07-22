package com.dal.group7.constants;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SQLConstants {
    public static final String STUDENT_BASIC = "student_basic";
    public static final String USER_CREDENTIAL = "user_credential";
    public static final String APPLICATION = "application";
    public static final String STUDENT_FINANCE = "student_finance";
    public static final String STUDENT_ACADEMIC = "student_academic";
    public static final String STUDENT_NON_ACADEMIC = "student_non_academic";
    public static final String SCHOLARSHIP = "scholarship";
    private static final String SELECT_ALL_QUERY = "select * from ";
    private static final String WHERE_ID = " where id = ?";
    private static final String WHERE_USER_ID = " where user_id = ?";
    private static final String WHERE_STUDENT_ID = " where student_id=?";
    private static final String WHERE_EMAIL_ID = " where email_id=?";
    private static final String WHERE_APPLICATION_ID =
            " where application_id = ?";
    private static final String WHERE_SCHOLARSHIP_ID =
            " where scholarship_id = ?";
    private static final String INSERT_NEW_STUDENT =
            "INSERT INTO student_basic (first_name, last_name, email_id, " +
                    "phone_number, passport_number, dob, gender, state, " +
                    "city, pincode, country) " +
                    "values (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_NEW_USER =
            "INSERT INTO user_credential (user_id, password, " +
                    "security_id, security_answer_one, security_answer_two, " +
                    "security_answer_three, " +
                    "role_type) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_NEW_INSTITUTE =
            "INSERT INTO institute_basic (institute_name, email_id, " +
                    "registration_code, phone_number," +
                    "address, state, city, country, pinCode)" +
                    "VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_NEW_SCHOLARSHIP =
            "INSERT INTO scholarship (name, effectiveDate, " +
                    "scholarship_amount," +
                    " criteria_girl, criteria_academic, criteria_sports)" +
                    "VALUES(?,?,?,?,?,?)";
    public static final String ONE = "1";
    private static final String INSERT_INTO = "INSERT INTO ";

    private static final String INSERT_STUDENT_FINANCE =
            INSERT_INTO + STUDENT_FINANCE +
                    " (email_id, bank_acc_num, bank_IFSC, annual_income, " +
                    "bank_name, bank_acc_holder_name)  VALUES(?,?,?,?,?,?)";
    private static final String INSERT_STUDENT_ACADEMIC =
            INSERT_INTO + STUDENT_ACADEMIC +
                    " (email_id, institute_id, `GPA-X`, `GPA-XII`, " +
                    "GPA_Bachelors," +
                    " `BOARD-X`, `BOARD-XII`, `backlog_count_X`, " +
                    "`backlog_count_XII`," +
                    " backlog_count_bachelors, joining_month_bachelors, " +
                    "graduation_month_bachelors) VALUES(?,?,?,?,?,?,?,?,?,?," +
                    "?,?) ";
    private static final String INSERT_STUDENT_NON_ACADEMIC =
            INSERT_INTO + STUDENT_NON_ACADEMIC +
                    " (email_id, national_sports_awards_count, " +
                    "state_sports_awards_count, district_sports_awards_count," +
                    " national_arts_awards_count,state_arts_awards_count, " +
                    "district_arts_awards_count)" +
                    "VALUES (?,?,?,?,?,?,?) ";
    private static final String INSERT_NEW_APPLICATION =
            INSERT_INTO + APPLICATION +
                    " (application_id, scheme_id, student_id, institute_id, " +
                    "application_status, academic_score, " +
                    "non_academic_score, profile_score)" +
                    " VALUES(?,?,?,?,?,?,?,?) ";

    // Application Status Variables
    public static final String SUBMITTED = "submitted";
    public static final String APPROVED = "approved";
    public static final String REJECTED = "rejected";
    public static final String CLOSED = "closed";
    public static final String PICKED = "picked";
    public static final String FUND_RECEIVED = "fund_received";
    public static final String FUND_ISSUED = "fund_issued";
    private static final String UPDATE_APPLICATION_SET = "update application set ";
    private static final String UPDATE_USER_CREDENTIAL_SET = "update user_credential set ";
    private static final String LAST_LOGIN_SET = "last_login_time = now()";
    private static final String STATUS = " = ?";
    private static final String AMOUNT = "tuition_amount=?, insurance_amount=?, travel_amount=?, " +
            "living_expenses_amount=?";

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

    public static String getSelectByStudentIdQuery(String table) {
        return SELECT_ALL_QUERY + table + WHERE_STUDENT_ID;
    }

    public static String getSelectByApplicationIdQuery() {
        return SELECT_ALL_QUERY + APPLICATION + WHERE_APPLICATION_ID;
    }

    public static String getSelectScholarshipByIdQuery() {
        return SELECT_ALL_QUERY + SCHOLARSHIP + WHERE_SCHOLARSHIP_ID;
    }

    public static String getSelectStudentFinanceByIdQuery() {
        return SELECT_ALL_QUERY + "student_finance" + WHERE_EMAIL_ID;
    }

    public static String getInsertNewScholarship() {
        return INSERT_NEW_SCHOLARSHIP;
    }

    public static String insertIntoTableAllFields(String table,
                                                  Integer numberOfFields) {
        final String params =
                IntStream.range(0, numberOfFields).mapToObj(i -> "?")
                        .collect(Collectors.joining(","));
        return "insert into " + table + " values(" + params + ");";
    }

    public static String getInsertNewStudent() {
        return INSERT_NEW_STUDENT;
    }

    public static String getInsertStudentFinance() {
        return INSERT_STUDENT_FINANCE;
    }

    public static String getInsertStudentAcademic() {
        return INSERT_STUDENT_ACADEMIC;
    }

    public static String getInsertStudentNonAcademic() {
        return INSERT_STUDENT_NON_ACADEMIC;
    }

    public static String getInsertNewApplication() {
        return INSERT_NEW_APPLICATION;
    }


    public static String getInsertNewUser() {
        return INSERT_NEW_USER;
    }

    public static String setStatusForApplication(String field) {
        return UPDATE_APPLICATION_SET + field + STATUS + WHERE_APPLICATION_ID;
    }

    public static String setAmountForApplication() {
        return UPDATE_APPLICATION_SET + AMOUNT + WHERE_APPLICATION_ID;
    }

    public static String setLastLoginForUser() {
        return UPDATE_USER_CREDENTIAL_SET + LAST_LOGIN_SET + WHERE_USER_ID;
    }

    public static String setFailedLoginCountForUser(String field) {
        return UPDATE_USER_CREDENTIAL_SET + field + STATUS + WHERE_USER_ID;
    }
}
