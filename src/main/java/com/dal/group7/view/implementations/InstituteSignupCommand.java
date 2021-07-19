package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.InstituteService;
import com.dal.group7.view.interfaces.Command;
import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.*;

public class InstituteSignupCommand extends Command {

    private String input;
    private Boolean result;
    public InstituteService instituteService;

    InstituteSignupCommand(InstituteService instituteService){ this.instituteService = instituteService; }

    @Override
    public void printView() {
        System.out.println(ENTER_INSTITUTE_FILE);
        System.out.print(PROMPT_PREFIX + FILLED_FILE);

    }

    @Override
    public void handle() {
        this.input = scanner.nextLine();
        try {
            System.out.println(INSERTING_INSTITUTE);
            instituteService.signup(input);
            System.out.println(PROGRAM_MESSAGE_PREFIX + SIGNED_UP_AS_INSTITUTE + PROGRAM_MESSAGE_POSTFIX);
            this.result = true;
        } catch (Exception e) {
            this.result = false;
        }

    }

    @Override
    public void setNextCommand() {
        this.nextCommand =
                result ? INSTITUTE_HOME.getCommand() : ERROR.getCommand();

    }
}