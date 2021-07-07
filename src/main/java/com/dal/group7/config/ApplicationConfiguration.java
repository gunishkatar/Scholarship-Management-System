package com.dal.group7.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.dal.group7.shared.constants.ApplicationConstants.*;

public class ApplicationConfiguration {
    private static Properties properties;

    private ApplicationConfiguration() {
        loadProperties();
    }

    public static String getDbUrl() {
       return properties.getProperty(DB_URL)
                .concat("?user=")
                .concat(properties.getProperty(USER))
                .concat("&password=")
                .concat(properties.getProperty(PASSWORD));
    }

    private void loadProperties() {
        try(InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
