package com.solvd.bank.persistence.jdbcimpl;

import com.solvd.bank.utils.jdbcconnectionutils.ConnectionPool;
import com.solvd.bank.utils.jdbcconnectionutils.MySQLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;

public abstract class BaseClassDAO<Entity> {

    protected static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    protected Integer autoIncrementValue;

    public abstract Entity createEntity(ResultSet resultSet) throws SQLException;

    protected abstract void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException;

    protected abstract void prepareSaveStatement(PreparedStatement preparedStatement, Entity entity) throws SQLException;

    protected abstract void prepareUpdateStatement(PreparedStatement preparedStatement, Entity entity) throws SQLException;

    protected abstract void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException;

    protected Integer getAutoIncrementValue() {
        return autoIncrementValue;
    }

    protected ArrayList<Entity> executeStatement(String query, String method, Entity entity, int id) {
        Connection connection = null;
        try {
            connection = MySQLFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            switch (routeToPrepareFunc(preparedStatement, method, entity, id)) {
                case "update":
                    int aiCheck = preparedStatement.executeUpdate();
                    if (aiCheck > 0) {
                        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                        updateAutoIncrementValue(generatedKeys);
                    }
                    break;
                case "query":
                    return getResultsFromResultSet(preparedStatement.executeQuery());
            }

        } catch (SQLException | InterruptedException e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    ConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                }
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

    private String routeToPrepareFunc(PreparedStatement preparedStatement, String method, Entity entity, int id) throws SQLException {
        switch (method) {
            case "getAll":
                return "query";
            case "getEntityById":
                prepareCreateStatement(preparedStatement, id);
                return "query";
            case "updateEntity":
                prepareUpdateStatement(preparedStatement, entity);
                return "update";
            case "removeEntityById":
                prepareRemoveStatement(preparedStatement, id);
                return "update";
            case "saveEntity":
                prepareSaveStatement(preparedStatement, entity);
                return "update";
            default:
                return "failed";
        }
    }

    private ArrayList<Entity> getResultsFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Entity> results = new ArrayList<>();
        while (resultSet.next()) {
            results.add(createEntity(resultSet));
        }
        return results;
    }

    private void updateAutoIncrementValue(ResultSet generatedKeys) throws SQLException {
        if (generatedKeys.next()) {
            autoIncrementValue = generatedKeys.getInt(1);
        } else {
            autoIncrementValue = null;
        }
    }
}
