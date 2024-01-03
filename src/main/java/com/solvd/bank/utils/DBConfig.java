package com.solvd.bank.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

public class DBConfig {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final Properties PROPERTIES;
    private static String propertiesPath = "/db.properties";
    ;

    static {
        PROPERTIES = new Properties();
        try (InputStream inputStream = DBConfig.class.getResourceAsStream(propertiesPath)) {
            if (inputStream != null) {
                PROPERTIES.load(inputStream);
            } else {
                LOGGER.error("Unable to load properties file. InputStream is null.");
                throw new RuntimeException("Unable to load properties file.");
            }
        } catch (IOException e) {
            LOGGER.error("Error loading properties file.", e);
            throw new RuntimeException("Error loading properties file.", e);
        }
    }

    public static final String URL = PROPERTIES.getProperty("url");
    public static final String USERNAME = PROPERTIES.getProperty("username");
    public static final String PASSWORD = PROPERTIES.getProperty("password");
}
