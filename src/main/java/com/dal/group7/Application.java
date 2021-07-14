package com.dal.group7;

import com.dal.group7.shared.EmailNotification;
import com.dal.group7.view.implementations.UserView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Application {
    void init() {
        UserView uv = new UserView();
        uv.options();
    }

    public static void main(String[] args) throws MessagingException {
        //new Application().init();
        /*test to check the email notification part*/
        EmailNotification.sendMailMsg("vn857734@dal.ca",
                "HEY",
                "<h2>Sent from my App File,</h2><p>hi there!!</p>");
    }
}
