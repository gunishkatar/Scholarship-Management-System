package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.JsonFileReader;
import com.dal.group7.service.implementation.MinistryScholarshipService;
import com.dal.group7.view.interfaces.Command;

import java.util.Scanner;

import static com.dal.group7.persistent.implementations.DaoFactory.SCHOLARSHIP;

public class MinistryHomeCommand extends Command {
    private Integer input;


    @Override
    public void printView() {
        System.out.println("Welcome to Ministry Portal!");
        System.out.println("1. List Applications");
        System.out.println("2. Create Scholarship");
        System.out.println("3. Quit");

    }

    @Override
    public void handle() {
        this.input = scanner.nextInt();
    }

    @Override
    public void getNextCommand() {
        switch (input) {
            case 1:
                // TODO: ADD Command
                break;
            case 2:
                nextCommand = new CreateScholarshipCommand(new MinistryScholarshipService(SCHOLARSHIP.createDao(),
                        new JsonFileReader()));
                break;
            case 3:
            default:
                nextCommand = new QuitCommand();
        }
    }
}
