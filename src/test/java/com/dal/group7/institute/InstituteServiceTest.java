package com.dal.group7.institute;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstituteServiceTest {

    public  String InstituteName = "NIT";

    @Test
    void setNameTest() {
        InstituteService institute = new InstituteService();
        institute.setName(InstituteName);

        assertTrue(InstituteName.compareTo("NIT") == 0, "Failed to set the Institute name");
    }

    @Test
    void getNameTest() {
        InstituteService institute = new InstituteService();
        institute.setName(InstituteName);

        assertTrue(institute.getName().compareTo("NIT") == 0, "Failed to get Institute name");
    }
}