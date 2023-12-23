package com.solved.bank.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final int POOL_SIZE = 5;
    private static final BlockingQueue<Connection> FREE_CONNECTIONS = new ArrayBlockingQueue<>(POOL_SIZE);
    private volatile static ConnectionPool instance;

    private ConnectionPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            FREE_CONNECTIONS.offer(makeConnection());
        }
    }

    public synchronized static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public static Connection getConnection() throws InterruptedException{
        LOGGER.info(Thread.currentThread().getName() + " - Waiting for connection");
        return FREE_CONNECTIONS.take();
    }

    public static void releaseConnection(Connection connection) {
        boolean added = FREE_CONNECTIONS.offer(connection);
        if (!added) {
            LOGGER.warn("Too many connections for queue, discarding");
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing connection: " + e.getMessage());
            }
        }
    }


    private static Connection makeConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return connection;
    }
}
