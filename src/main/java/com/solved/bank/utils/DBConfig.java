package com.solved.bank.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConfig {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static String propertiesPath = "/db.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = DBConfig.class.getResourceAsStream(propertiesPath)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                LOGGER.error("Unable to load properties file. InputStream is null.");
                throw new RuntimeException("Unable to load properties file.");
            }
        } catch (IOException e) {
            LOGGER.error("Error loading properties file.", e);
            throw new RuntimeException("Error loading properties file.", e);
        }
    }

    public static final String URL = properties.getProperty("url");
    public static final String USERNAME = properties.getProperty("username");
    public static final String PASSWORD = properties.getProperty("password");

}
