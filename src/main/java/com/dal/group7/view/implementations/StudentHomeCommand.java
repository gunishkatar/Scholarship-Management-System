package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.view.implementations.CommandFactory.QUIT;

public class StudentHomeCommand extends Command {
    private Integer input;

    @Override
    public void printView() {
        System.out.println(
                "As a student, You can now access the following menu");
        System.out.println("1. List Scholarship Schemes");
        System.out.println("2. Apply for a Scheme");
        System.out.println("3. Check the status of the Applied Scheme");
        System.out.println("4. Logout" + System.lineSeparator());
        System.out.print(">> Please select your option : ");
    }

    @Override
    public void handle() {
        this.input = scanner.nextInt();
    }

    @Override
    public void setNextCommand() {
        switch (input) {
            case 1:
                // TODO: LIST Scholarship Command
                break;
            case 2:
                // TODO: APPLY for scheme
                break;
            case 3:
                // TODO: CHECK STATUS
                break;
            case 4:
                // TODO: LOGOUT
            default:
                this.nextCommand = QUIT.getCommand();
        }
    }
}
