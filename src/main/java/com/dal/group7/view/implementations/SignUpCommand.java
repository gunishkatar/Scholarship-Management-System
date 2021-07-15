package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.view.implementations.CommandFactory.QUIT;
import static com.dal.group7.view.implementations.CommandFactory.STUDENT_SIGNUP;

public class SignUpCommand extends Command {

    private Integer input;

    @Override
    public void printView() {
        System.out.println("Sign Up As : ");
        System.out.println("1. Student");
        System.out.println("2. Institute");
        System.out.print(">> Please select your option: ");
    }

    @Override
    public void handle() {
        this.input = scanner.nextInt();
    }

    @Override
    public void setNextCommand() {
        switch (input) {
            case 1:
                nextCommand = STUDENT_SIGNUP.getCommand();
                break;
            case 2:
                // TODO: ADD COMMAND
                break;
            default:
                nextCommand = QUIT.getCommand();
        }
    }
}
