package com.dal.group7.persistent.model;


import java.sql.ResultSet;
import java.sql.SQLException;

import static com.dal.group7.constants.FieldConstants.ID;
import static com.dal.group7.constants.FieldConstants.NAME;

public class Institute {
    private Integer id;
    private String name;
    private String emailId;
    private Integer registrationCode;
    private Integer phoneNumber;
    private String address;
    private String state;
    private String city;
    private String country;
    private Integer pinCode;

    public Institute() {
    }

    public Institute(Integer id, String name, String emailId, Integer registrationCode,
                     Integer phoneNumber, String address,String state, String city,String country,
                     Integer pinCode) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.registrationCode = registrationCode;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.state = state;
        this.city = city;
        this.country = country;
        this.pinCode = pinCode;
    }

    public Institute from(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(ID);
        name = resultSet.getString(NAME);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public Integer getRegistrationCode() {
        return registrationCode;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Integer getPinCode() {
        return pinCode;
    }
}
