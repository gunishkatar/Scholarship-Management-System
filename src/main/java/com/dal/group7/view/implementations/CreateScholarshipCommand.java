package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.MinistryScholarshipService;
import com.dal.group7.view.interfaces.Command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.MINISTRY_HOME;

public class CreateScholarshipCommand extends Command {
    final Scanner scanner = new Scanner(System.in);
    private final MinistryScholarshipService ministryScholarshipService;
    private boolean successFul;

    public CreateScholarshipCommand(MinistryScholarshipService ministryScholarshipService) {
        this.ministryScholarshipService = ministryScholarshipService;
    }

    @Override
    public void printView() {
        System.out.println("Enter the details of the Scholarship using the JSON file template created " +
                "in /var/tmp/addScholarship.json.");
        System.out.print(">> Enter the complete path of the filled file: ");
    }

    @Override
    public void handle() {
        String input = scanner.nextLine();
        try {
            ministryScholarshipService.saveScholarship(input);
            System.out.println("<<<< Successfully Created the Scholarship. Students can avail it now. >>>>");
            this.successFul = true;
        } catch (SQLException | IOException exception) {
           this.successFul = false;
        }
    }

    @Override
    public void setNextCommand() {
        this.nextCommand = this.successFul ? MINISTRY_HOME.getCommand() : ERROR.getCommand();
    }
}
