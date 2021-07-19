package com.dal.group7.service.implementation;

import com.dal.group7.persistent.implementations.InstituteDao;
import com.dal.group7.persistent.model.Institute;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class InstituteServiceTest {

    @Mock
    private InstituteDao instituteDao;

    @Mock
    private JsonFileReader jsonFileReader;

    @Test
    public void isValidInstitute() {
        Institute institute = new Institute(1,"name","institute@dal.ca",5000,1111,
                "LakeLouise","NovaScotia","Halifax","Country",1234);

        InstituteService instituteService = new InstituteService(instituteDao, jsonFileReader);
        Assertions.assertTrue(instituteService.isValid(institute));
    }

    @Test
    public void isNotValidInstitute() {
        Institute institute = new Institute(1,"name","institute@dal.ca",0,1111,
                "LakeLouise","NovaScotia","Halifax","Country",1234);

        InstituteService instituteService = new InstituteService(instituteDao, jsonFileReader);
        Assertions.assertFalse(instituteService.isValid(institute));
    }

    public void signup(){

    }

    public void login(){

    }
}