package com.solved.bank.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class ConnectionPool {

    private static ConnectionPool instance = null;
    private static final int POOL_SIZE = 5;
    private static Vector<Connection> freeConnections = new Vector<>();
    private static Vector<Connection> usedConnections = new Vector<>();

    private ConnectionPool(){
    }


}
