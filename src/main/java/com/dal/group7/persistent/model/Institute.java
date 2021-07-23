package com.dal.group7.persistent.model;


import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

import static com.dal.group7.constants.FieldConstants.ID;
import static com.dal.group7.constants.FieldConstants.NAME;

public class Institute {
    private Integer id;
    private String name;
    private String emailId;
    private Integer registrationCode;
    private String password;
    private String reConfirmPassword;
    private String address;
    private String city;
    private String state;
    private Integer phoneNumber;
    private String country;
    private Integer pinCode;
    private Boolean isBlacklisted;

    public Institute() {
    }

    public Institute(Integer id, String name, String emailId, Integer registrationCode,String password,
                     String reConfirmPassword, Integer phoneNumber, String address,String city, String state,
                     String country, Integer pinCode, Boolean isBlacklisted) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.registrationCode = registrationCode;
        this.password = password;
        this.reConfirmPassword = reConfirmPassword;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.isBlacklisted = isBlacklisted;
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

    public String getEmailId() { return emailId;}

    public Integer getRegistrationCode() {
        return registrationCode;
    }

    public String getPassword() {
        return password;
    }

    public String getReConfirmPassword() { return reConfirmPassword; }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public Boolean getIsBlacklisted() {
        return isBlacklisted;
    }

    public Institute from(JSONObject jsonObject) {
        this.id = new Random().nextInt();
        this.name= jsonObject.getString("name");
        this.emailId = jsonObject.getString("email");
        this.registrationCode = jsonObject.getInt("regd_code");
        this.password = jsonObject.getString("password");
        this.reConfirmPassword = jsonObject.getString("re_confirm_password");
        this.address = jsonObject.getString("address");
        this.city = jsonObject.getString("city");
        this.state = jsonObject.getString("institute_state");
        this.phoneNumber = jsonObject.getInt("institute_contact");
        this.country = jsonObject.getString("country");
        this.pinCode = jsonObject.getInt("pincode");
        this.isBlacklisted = jsonObject.getBoolean("isblacklisted");
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institute that = (Institute) o;
        return Objects.equals(name, that.name) && Objects.equals(emailId , that.emailId) && Objects.equals(registrationCode, that.registrationCode)
                && Objects.equals(password, that.password) && Objects.equals(reConfirmPassword, that.reConfirmPassword)
                && Objects.equals(phoneNumber, that.phoneNumber ) && Objects.equals(address, that.address)
                && Objects.equals(state, that.state) && Objects.equals(city, that.city) && Objects.equals(country, that.country)
                && Objects.equals(pinCode, that.pinCode) && Objects.equals(isBlacklisted, that.isBlacklisted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emailId, registrationCode, password, reConfirmPassword, phoneNumber, address, state, city, country,
                pinCode,isBlacklisted);
    }
}