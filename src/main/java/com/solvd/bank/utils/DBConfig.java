package com.solvd.bank.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConfig {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static String propertiesPath = "/db.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = DBConfig.class.getResourceAsStream(propertiesPath)) {
            if (inputStream != null) {
                properties.load(inputStream);
                URL = properties.getProperty("url");
                USERNAME = properties.getProperty("username");
                PASSWORD = properties.getProperty("password");
            } else {
                LOGGER.error("Unable to load properties file. InputStream is null.");
                throw new RuntimeException("Unable to load properties file.");
            }
        } catch (IOException e) {
            LOGGER.error("Error loading properties file.", e);
            throw new RuntimeException("Error loading properties file.", e);
        }
    }

    public static synchronized Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return connection;
    }
}
