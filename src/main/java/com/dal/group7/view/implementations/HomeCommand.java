package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

public class HomeCommand extends Command {
    private Integer input;


    @Override
    public void handle() {
        this.input = scanner.nextInt();
    }


    @Override
    public void getNextCommand() {
        switch (input) {
            case 1:
                nextCommand = new SelectLoginUserCommand();
                break;
            case 2:
                nextCommand = new SignUpCommand();
                break;
            case 3:
                nextCommand = new GuestCommand();
                break;
            default:
               nextCommand = new QuitCommand();
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
