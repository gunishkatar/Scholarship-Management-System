package com.dal.group7.service.implementation;

import com.dal.group7.persistent.implementations.InstituteDao;
import com.dal.group7.persistent.model.Institute;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

class InstituteServiceTest {
    static final Institute INSTITUTE = new Institute(1,"name","dal.ca",5000,"abc",
            "abc", 1111,"LakeLouise","NovaScotia","Halifax",
            "Canada",1234);

    @Mock
    private InstituteDao instituteDao;

    @Mock
    private JsonFileReader jsonFileReader;

    @Mock
    private InstituteService instituteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void isValidInstitute() {
        Institute institute = new Institute(1,"name","institute@dal.ca",5000,
                "abc","abc",1111,
                "LakeLouise","NovaScotia","Halifax","Country",1234);

        InstituteService instituteService = new InstituteService(instituteDao, jsonFileReader);
        Assertions.assertTrue(instituteService.isValid(institute));
    }

    @Test
    public void isNotValidInstitute() {
        Institute institute = new Institute(1,"name","institute@dal.ca",
                0,"abc","abc",1111, "LakeLouise",
                "NovaScotia","Halifax","Country",1234);

        InstituteService instituteService = new InstituteService(instituteDao, jsonFileReader);
        Assertions.assertFalse(instituteService.isValid(institute));
    }

    public void signup(){

    }

    public void login(){

    }
}