package com.dal.group7.view.implementations;

import com.dal.group7.persistent.model.Application;
import com.dal.group7.service.implementation.InstituteApplicationService;
import com.dal.group7.view.interfaces.Command;

import java.util.List;

import static com.dal.group7.constants.ViewConstants.STARS;
import static com.dal.group7.constants.ViewConstants.VIEW_APPLICATION;
import static com.dal.group7.view.implementations.CommandFactory.*;

public class ListApplicationMinistryCommand extends Command {

    private boolean result;
    private InstituteApplicationService instituteApplicationService;

    public ListApplicationMinistryCommand(InstituteApplicationService instituteApplicationService){
        this.instituteApplicationService = instituteApplicationService;
    }

    @Override
    public void printView() {

        System.out.println(VIEW_APPLICATION);

    }

    @Override
    public void handle() {

        try {
            printOnConsole(instituteApplicationService.displayApprovedApplications());
            this.result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.result = false;
        }

    }

    private void printOnConsole(List<Application> applications) {
        for (Application scheme : applications) {
            System.out.print(System.lineSeparator());
            System.out.println(STARS + scheme.getApplicationId() + STARS);
//            System.out.println(EFFECTIVE_FROM + scheme.getEffectiveDate());
//            System.out.println(
//                    AMOUNT_GRANT + scheme.getTuitionAmount());
//            System.out.println(
//                    GIRL_CHILD_SPECIFIC + scheme.getCriteriaGirlChild());
//            System.out.println(
//                    ACADEMIC_SPECIFIC + scheme.getCriteriaAcademics());
//            System.out
//                    .println(SPORTS_SPECIFIC + scheme.getCriteriaSports());
        }
        System.out.print(System.lineSeparator());
    }

    @Override
    public void setNextCommand() {
        this.nextCommand = this.result ? MINISTRY_HOME.getCommand() : ERROR.getCommand();
    }

}
