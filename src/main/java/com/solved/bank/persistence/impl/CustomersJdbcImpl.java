package com.solved.bank.persistence.impl;

import com.solved.bank.domain.Associates;
import com.solved.bank.domain.Customers;
import com.solved.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomersJdbcImpl extends BaseClassJdbcImpl<Customers> implements IBaseRepository<Customers> {

    @Override
    protected Customers getAllHelper(ResultSet resultSet) {
        Customers customers = new Customers();
        try {
            Associates associate = new AssociatesJdbcImpl().getEntityById(resultSet.getInt("associate_id"));

            customers.setAssociate(associate);
            customers.setCreditScore(resultSet.getString("creditScore"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return customers;
    }

    @Override
    protected Customers getEntityByIdHelper(PreparedStatement preparedStatement, int id) {
        try {
            preparedStatement.setInt(1, id);
            return processResultSet(preparedStatement).get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    protected void saveEntityHelper(PreparedStatement preparedStatement, Customers customers) {
        try {
            preparedStatement.setInt(1, customers.getAssociate().getId());
            preparedStatement.setString(2, customers.getCreditScore());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    protected void updateEntityHelper(PreparedStatement preparedStatement, Customers customers) {
        try {
            preparedStatement.setInt(1, customers.getAssociate().getId());
            preparedStatement.setString(2, customers.getCreditScore());
            preparedStatement.setInt(3, customers.getAssociate().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    protected void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) {
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void saveEntity(Customers customers) {
        String query = "INSERT INTO customers (associate_id, creditScore) VALUES (?, ?)";
        executeStatement(query, "saveEntity", customers);
    }

    @Override
    public Customers getEntityById(int id) {
        String query = "SELECT * FROM customers WHERE associate_id = ?";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public void updateEntity(Customers customers) {
        String query = "UPDATE customers SET associate_id = ?, creditScore = ? WHERE associate_id = ?";
        executeStatement(query, "updateEntity", customers);
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM customers WHERE associate_id = ?";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public List<Customers> getAll() {
        String query = "SELECT * FROM customers";
        return executeStatement(query, "getAll");
    }
}
