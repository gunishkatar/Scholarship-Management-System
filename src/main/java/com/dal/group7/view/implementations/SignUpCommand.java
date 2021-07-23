package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.QUIT;
import static com.dal.group7.view.implementations.CommandFactory.STUDENT_SIGNUP;
import static com.dal.group7.view.implementations.CommandFactory.INSTITUTE_SIGNUP;


public class SignUpCommand extends Command {
    private Integer input;

    @Override
    public void printView() {
        System.out.println(SIGN_UP_AS);
        System.out.println(STUDENT);
        System.out.println(INSTITUTE);
        System.out.print(PROMPT_PREFIX + PLEASE_SELECT_YOUR_OPTION);
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
                nextCommand = INSTITUTE_SIGNUP.getCommand();
                break;
            default:
                nextCommand = QUIT.getCommand();
        }
    }
}
