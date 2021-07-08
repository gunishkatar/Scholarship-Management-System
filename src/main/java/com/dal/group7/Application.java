package com.dal.group7;

import com.dal.group7.view.implementations.UserView;

public class Application {
    void init() {
        UserView uv = new UserView();
        uv.options();
    }

    public static void main(String[] args) {
        
        new Application().init();
    }
}
