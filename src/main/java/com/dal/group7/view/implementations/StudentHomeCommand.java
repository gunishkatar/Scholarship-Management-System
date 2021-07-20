package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.APPLY_FOR_SCHEME;
import static com.dal.group7.view.implementations.CommandFactory.QUIT;

public class StudentHomeCommand extends Command {
    private Integer input;

    @Override
    public void printView() {
        System.out.println(STUDENT_MENU);
        System.out.println(SCHOLARSHIP_SCHEMES);
        System.out.println(APPLY_FOR_A_SCHEME);
        System.out.println(CHECK_THE_STATUS_OF_THE_APPLIED_SCHEME);
        System.out.println(LOGOUT_LAST + System.lineSeparator());
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
                this.nextCommand = APPLY_FOR_SCHEME.getCommand();
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
