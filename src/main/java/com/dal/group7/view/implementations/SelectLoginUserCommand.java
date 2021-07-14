package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.view.implementations.CommandFactory.QUIT;

public class SelectLoginUserCommand extends Command {
    private Integer input;

    @Override
    public void printView() {
        System.out.println("Login as: ");
        System.out.println("1. Student");
        System.out.println("2. Institute");
        System.out.println("3. Ministry");
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
                // TODO: ADD Command
                break;
            case 2:
                // TODO: ADD Command
                break;
            case 3:
                nextCommand = CommandFactory.MINISTRY_LOGIN.getCommand();
                break;
            default:
                nextCommand = QUIT.getCommand();
        }
    }
}
