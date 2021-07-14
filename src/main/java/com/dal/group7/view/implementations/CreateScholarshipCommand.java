package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.MinistryScholarshipService;
import com.dal.group7.view.interfaces.Command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateScholarshipCommand extends Command {
    final Scanner scanner = new Scanner(System.in);
    private final MinistryScholarshipService ministryScholarshipService;
    private boolean successFul;

    public CreateScholarshipCommand(MinistryScholarshipService ministryScholarshipService) {
        this.ministryScholarshipService = ministryScholarshipService;
    }

    @Override
    public void printView() {
        System.out.println("Enter the path to the filled json: ");
    }

    @Override
    public void handle() {
        String input = scanner.nextLine();
        try {
            ministryScholarshipService.saveScholarship(input);
            System.out.println("Sucessfully created Scholarship");
            this.successFul = true;
        } catch (SQLException | IOException exception) {
           this.successFul = false;
        }
    }

    @Override
    public void getNextCommand() {
        this.nextCommand = this.successFul ? new MinistryHomeCommand() : new ErrorCommand();
    }
}
