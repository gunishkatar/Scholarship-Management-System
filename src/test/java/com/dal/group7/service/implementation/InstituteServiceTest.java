package com.dal.group7.service.implementation;

import com.dal.group7.persistent.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InstituteServiceTest {
    @Test
    public void isValidInstitute() {
        Student student = new Student("Bruce", "Wayne", "bruce.wayne@dal.ca",
                "878-98287", "R198A2J", "somePass", "somePass",
                "2020-09-10 08:22:31", "male", "Metropolis", "Gotham", "3AT7A5",
                "USA", "Liverpool", "Cheese B", "Pheobe Buffay");

//        InstituteService instituteService = new InstituteService();
//        Assertions.assertTrue(instituteService.isValid(student));
    }
}