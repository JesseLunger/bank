package com.solvd.bank.persistence.impl;

import com.solvd.bank.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public abstract class BaseClassJdbcImpl<Entity> {

    protected static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    protected abstract Entity getAllHelper(ResultSet resultSet) throws SQLException;
    protected abstract Entity getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException;
    protected abstract void saveEntityHelper(PreparedStatement preparedStatement, Entity entity) throws SQLException;
    protected abstract void updateEntityHelper(PreparedStatement preparedStatement, Entity entity) throws SQLException;
    protected abstract void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException;

    protected ArrayList<Entity> executeStatement(String query, String method, Entity entity, int id) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                return routeToAbstract(preparedStatement, method, entity, id);
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return null;
    }

    protected ArrayList<Entity> executeStatement(String query, String method) {
        return executeStatement(query, method, null, -1);
    }

    protected void executeStatement(String query, String method, Entity entity) {
        executeStatement(query, method, entity, -1);
    }

    protected ArrayList<Entity> executeStatement(String query, String method, int id) {
        return executeStatement(query, method, null, id);
    }

    private ArrayList<Entity> routeToAbstract(PreparedStatement preparedStatement, String method, Entity entity, int id) throws SQLException {
        switch (method) {
            case "getAll":
                return processResultSet(preparedStatement);
            case "getEntityById":
                return new ArrayList<>(Collections.singletonList(getEntityByIdHelper(preparedStatement, id)));
            case "updateEntity":
                updateEntityHelper(preparedStatement, entity);
                return null;
            case "removeEntityById":
                removeEntityByIdHelper(preparedStatement, id);
                return null;
            case "saveEntity":
                saveEntityHelper(preparedStatement, entity);
                return null;
            default:
                LOGGER.error("Incorrect method name");
                return  null;
        }
    }

    protected ArrayList<Entity> processResultSet(PreparedStatement preparedStatement){
        ArrayList<Entity> results = new ArrayList<>();
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                results.add(getAllHelper(resultSet));
            }
        } catch (SQLException e){
            LOGGER.error(e.getMessage());
        }
        return results;
    }

    public Integer getAutoIncrementValue(PreparedStatement preparedStatement) throws SQLException{
        int AICheck = preparedStatement.executeUpdate();
        if (AICheck > 0){
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                return generatedKeys.getInt(1);
            }
        }
        return null;
    }
}
