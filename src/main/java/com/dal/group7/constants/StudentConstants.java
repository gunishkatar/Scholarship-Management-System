package com.dal.group7.constants;

import java.util.ArrayList;

public class StudentConstants {

    private static final ArrayList<String> invalidDomains = new ArrayList<>();

    private static final String emailDelimiter = "@";

    public static ArrayList<String> getInvalidDomains() {
        invalidDomains.add("gmail.com");
        invalidDomains.add("yahoo.com");
        invalidDomains.add("outlook.com");
        invalidDomains.add("facebook.com");
        return invalidDomains;
    }

    public static String getEmailDelimiter() {
        return emailDelimiter;
    }

}
