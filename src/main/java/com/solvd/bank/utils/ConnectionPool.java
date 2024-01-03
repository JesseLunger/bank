package com.solvd.bank.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final int POOL_SIZE = 50;
    private static ConnectionPool instance;
    private static Vector<Connection> freeConnections = new Vector<>();
    private static Vector<Connection> usedConnections = new Vector<>();

    private ConnectionPool() {
    }

    public static ConnectionPool initializePool() {
        if (instance == null) {
            instance = new ConnectionPool();
            createPool();
        }
        return instance;
    }

    private static void createPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            freeConnections.add(createConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD));
        }
    }

    public static synchronized Connection getConnection() throws InterruptedException {
        initializePool();
        Connection connection = freeConnections.firstElement();
        freeConnections.remove(connection);
        usedConnections.add(connection);
        return connection;
    }

    public static synchronized void releaseConnection(Connection connection) throws SQLException {
        if (usedConnections.remove(connection)) {
            freeConnections.add(connection);
        } else {
            throw new SQLException();
        }
    }

    private static Connection createConnection(String url, String username, String password) {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
