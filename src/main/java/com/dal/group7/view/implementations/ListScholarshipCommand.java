package com.dal.group7.view.implementations;

import com.dal.group7.persistent.model.Scholarship;
import com.dal.group7.service.implementation.MinistryScholarshipService;
import com.dal.group7.view.interfaces.Command;

import java.util.List;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.STUDENT_HOME;

public class ListScholarshipCommand extends Command {

    private boolean result;
    private MinistryScholarshipService ministryScholarshipService;

    public ListScholarshipCommand(
            MinistryScholarshipService ministryScholarshipService) {
        this.ministryScholarshipService = ministryScholarshipService;
    }

    @Override
    public void printView() {
        System.out.println(VIEW_SCHOLARSHIP);
    }

    @Override
    public void handle() {
        try {
            printOnConsole(ministryScholarshipService.displayScholarships());
            this.result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.result = false;
        }
    }

    private void printOnConsole(List<Scholarship> scholarships) {
        for (Scholarship scheme : scholarships) {
            System.out.print(System.lineSeparator());
            System.out.println(STARS + scheme.getScholarShipName() + STARS);
            System.out.println(EFFECTIVE_FROM + scheme.getEffectiveDate());
            System.out.println(
                    AMOUNT_GRANT + scheme.getScholarshipAmount());
            System.out.println(
                    GIRL_CHILD_SPECIFIC + scheme.getCriteriaGirlChild());
            System.out.println(
                    ACADEMIC_SPECIFIC + scheme.getCriteriaAcademics());
            System.out
                    .println(SPORTS_SPECIFIC + scheme.getCriteriaSports());
        }
        System.out.print(System.lineSeparator());
    }

    @Override
    public void setNextCommand() {
        this.nextCommand =
                this.result ? STUDENT_HOME.getCommand() : ERROR.getCommand();
    }
}
