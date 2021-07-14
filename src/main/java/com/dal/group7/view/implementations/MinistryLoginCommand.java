package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.MinistryLoginService;
import com.dal.group7.view.interfaces.Command;

import java.util.Scanner;

public class MinistryLoginCommand extends Command {

    final Scanner scanner = new Scanner(System.in);
    private String userName;
    private String password;
    private MinistryLoginService loginService;
    private String userType;

    public MinistryLoginCommand(MinistryLoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void printView() {
        System.out.println("Enter Username: ");
        this.userName = scanner.nextLine();
        System.out.println("Enter Password: ");
        this.password = scanner.nextLine();
    }

    @Override
    public void handle() {
        try {
            var userCredential = loginService.userLogin(userName, password);
            this.userType = userCredential.getRoleType();
        } catch (Exception exception) {
            this.userType = "Error";
        }
    }

    @Override
    public void getNextCommand() {
        switch (userType) {
            case "Student":
                // create command
                break;
            case "Institute":
                // create institute
                break;
            case "Ministry":
                nextCommand = new MinistryHomeCommand();
                break;
            case "Error":
            default:
                nextCommand = new ErrorCommand();
                break;
        }

    }
}
