package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.StudentService;
import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.STUDENT_HOME;

public class StudentSignupCommand extends Command {
    private String input;
    private Boolean result;
    public StudentService studentService;


    StudentSignupCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void printView() {
        System.out.println(
                "Enter the student details using the JSON file template " +
                        "in /var/tmp/addStudent.json");
        System.out.print(">> Enter the complete path of the filled file: ");
    }

    @Override
    public void handle() {
        this.input = scanner.nextLine();
        try {
            System.out.println("Inserting Student....");
            studentService.signup(input);
            System.out.println("<<< Successfully signed up as student  >>>");
            this.result = true;
        } catch (Exception e) {
            this.result = false;
        }
    }

    @Override
    public void setNextCommand() {
        this.nextCommand =
                result ? STUDENT_HOME.getCommand() : ERROR.getCommand();
    }
}
