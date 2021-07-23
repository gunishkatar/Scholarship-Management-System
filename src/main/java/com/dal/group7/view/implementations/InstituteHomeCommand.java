package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.INSTITUTE_APPROVE_REJECT;
import static com.dal.group7.view.implementations.CommandFactory.QUIT;

public class InstituteHomeCommand extends Command {
    private Integer input;

    @Override
    public void printView() {
        System.out.println(INSTITUTE_MENU);
        System.out.println(SCHOLARSHIP_SCHEMES);
        System.out.println(VIEW_APPROVED_STUDENT_APPLICATIONS);
        System.out.println(APPROVE_REJECT_APPLICATION);
        System.out.println(LOGOUT + System.lineSeparator());
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
                // TODO: LIST Scholarship Command
                break;
            case 2:
                // TODO: View Approved Student Application
                break;
            case 3:
                nextCommand = INSTITUTE_APPROVE_REJECT.getCommand();
                break;
            case 4:
            default:
                this.nextCommand = QUIT.getCommand();
        }

    }
}
