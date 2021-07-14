package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.view.implementations.CommandFactory.QUIT;

public class ErrorCommand extends Command {
    @Override
    public void printView() {
        System.out.println("<<<< Error occurred while execution >>>>");

    }

    @Override
    public void handle() {
        // TODO: ADD content
    }

    @Override
    public void setNextCommand() {
        nextCommand = QUIT.getCommand();
    }
}
