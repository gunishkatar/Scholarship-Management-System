package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import java.util.Scanner;

public class ErrorCommand extends Command {
    @Override
    public void printView() {
        System.out.println("Error occured while execution");

    }

    @Override
    public void handle() {
        // TODO: ADD content
    }

    @Override
    public void getNextCommand() {
        nextCommand = new QuitCommand();
    }
}
