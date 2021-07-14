package com.dal.group7;

import com.dal.group7.view.implementations.HomeCommand;
import com.dal.group7.view.implementations.UserView;

public class Application {
    void init() {
        UserView uv = new UserView();
        uv.options();
    }

    public static void main(String[] args) {
        final HomeCommand homeCommand = new HomeCommand();
        homeCommand.execute();
//        new Application().init();
    }
}
