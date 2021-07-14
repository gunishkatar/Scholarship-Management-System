package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.view.implementations.CommandFactory.*;

public class HomeCommand extends Command {
    private Integer input;


    @Override
    public void handle() {
        this.input = scanner.nextInt();
    }


    @Override
    public void setNextCommand() {
        switch (input) {
            case 1:
                nextCommand = SELECT_LOGIN_USER.getCommand();
                break;
            case 2:
                nextCommand = SIGN_UP.getCommand();
                break;
            case 3:
                nextCommand = GUEST.getCommand();
                break;
            default:
                nextCommand = QUIT.getCommand();
        }
    }

    @Override
    public void printView() {
        System.out.println("Welcome to Scholarship Portal!");
        System.out.println("1. Login");
        System.out.println("2. Sign up");
        System.out.println("3. Guest View");
    }
}
