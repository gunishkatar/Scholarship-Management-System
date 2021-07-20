package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.FieldConstants.ONE;
import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.INSTITUTE_HOME;

public class InstituteDecisionCommand extends Command {
  private String applicationNumber;
  private Integer input = 0;
  private boolean confirm;
  private boolean error;

  @Override
  public void printView() {
    System.out.print(PROMPT_PREFIX + APPLICATION_NUMBER);
    applicationNumber = scanner.nextLine();
    //if application exist
    //show application
    //if doesn't
    //show error
    System.out.println(APPROVE);
    System.out.println(REJECT);
    System.out.println(EXIT);
    System.out.print(PROMPT_PREFIX + PLEASE_SELECT_YOUR_OPTION);
    input = scanner.nextInt();

  }

  @Override
  public void handle() {
    switch (input) {
      case 1:
        getConfirmation();
        if(confirm) {
          //call approve method
          //forward to ministry
          // update the status
          System.out.println(PROGRAM_MESSAGE_PREFIX + "Application has been Approved!" + PROGRAM_MESSAGE_POSTFIX);
        }
        break;
      case 2:
        getConfirmation();
        if(confirm) {
          //call reject method
          // update the status
          System.out.println(PROGRAM_MESSAGE_PREFIX + "Application has been Rejected!" + PROGRAM_MESSAGE_POSTFIX);
        }
        break;
      case 3:
        break;
      case 0:
      default:
        this.error = true;
        break;
    }
  }

  private void getConfirmation() {
    System.out.println(CONFIRM_YOUR_SELECTION);
    System.out.println(CONFIRM);
    System.out.println(EXIT1);
    System.out.print(PROMPT_PREFIX + PLEASE_SELECT_YOUR_OPTION);
    input = scanner.nextInt();
    confirm = input.equals(ONE);
  }

  @Override
  public void setNextCommand() {
      nextCommand = error ? ERROR.getCommand() : INSTITUTE_HOME.getCommand();
  }
}
