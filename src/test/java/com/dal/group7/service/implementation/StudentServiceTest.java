package com.dal.group7.service.implementation;

import com.dal.group7.persistent.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentServiceTest {

    @Test
    public void isValidStudent() {
        Student student = new Student("Bruce", "Wayne", "bruce.wayne@dal.ca",
                "878-98287", "R198A2J", "somePass", "somePass",
                "2020-09-10 08:22:31", "male", "Metropolis", "Gotham", "3AT7A5",
                "USA", "Liverpool", "Cheese B", "Pheobe Buffay");

        StudentService studentService = new StudentService();
        Assertions.assertTrue(studentService.isValid(student));
    }

    @Test
    public void isNotValidStudent() {
        Student student = new Student("Bruce", "Wayne", "bruce.wayne@dal.ca",
                "878-98287", "R198A2J", "differentPass", "somePass",
                "2020-09-10 08:22:31", "male", "Metropolis", "Gotham", "3AT7A5",
                "", "Liverpool", "", "");

        StudentService studentService = new StudentService();
        Assertions.assertFalse(studentService.isValid(student));
    }

}