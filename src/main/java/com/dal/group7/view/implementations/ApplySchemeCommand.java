package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.StudentApplySchemeService;
import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.STUDENT_HOME;

public class ApplySchemeCommand extends Command {

    public Boolean result;
    public String input;
    public StudentApplySchemeService studentApplySchemeService;

    ApplySchemeCommand(StudentApplySchemeService studentApplySchemeService) {
        this.studentApplySchemeService = studentApplySchemeService;
    }

    @Override
    public void printView() {
        System.out.println(ENTER_APPLY_SCHEME_FILE);
        System.out.print(PROMPT_PREFIX + FILLED_FILE);
    }

    @Override
    public void handle() {
        this.input = scanner.nextLine();
        try {
            System.out.println(APPLYING_SCHOLARSHIP);
            studentApplySchemeService.applyScheme(input);
            System.out.println(PROGRAM_MESSAGE_PREFIX + SIGNED_UP_AS_STUDENT +
                    PROGRAM_MESSAGE_POSTFIX);
            this.result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.result = false;
        }
    }

    @Override
    public void setNextCommand() {
        this.nextCommand =
                this.result ? STUDENT_HOME.getCommand() : ERROR.getCommand();
    }
}
