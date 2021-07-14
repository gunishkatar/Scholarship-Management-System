package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

public class QuitCommand extends Command {

    @Override
    public void execute() {
        printView();
        handle();
    }

    @Override
    public void printView() {
        System.out.println("End of program");
    }

    @Override
    public void handle() {
        //TODO: ADD content
    }

    @Override
    public void getNextCommand() {
        //TODO: ADD content
    }
}
