package com.dal.group7.view.implementations;

import com.dal.group7.persistent.implementations.ConnectionManager;
import com.dal.group7.persistent.implementations.DaoFactory;
import com.dal.group7.persistent.implementations.PwdEncryptDao;
import com.dal.group7.service.implementation.MinistryLoginService;
import com.dal.group7.shared.PwdEncrypt;
import com.dal.group7.view.interfaces.Command;

public class SelectLoginUserCommand extends Command {
    private Integer input;

    @Override
    public void printView() {
        System.out.println("Select User");
        System.out.println("1. Student");
        System.out.println("2. Institute");
        System.out.println("3. Ministry");
    }

    @Override
    public void handle() {
        this.input = scanner.nextInt();
    }

    @Override
    public void getNextCommand() {
        switch (input) {
            case 1:
                // TODO: ADD Command
                break;
            case 2:
                // TODO: ADD Command
                break;
            case 3:
                nextCommand = new LoginCommand(new MinistryLoginService(DaoFactory.USER_CREDENTIALS.createDao(),
                        new PwdEncrypt(new PwdEncryptDao(new ConnectionManager()))));
                break;
            default:
                nextCommand = new QuitCommand();
        }
    }
}
