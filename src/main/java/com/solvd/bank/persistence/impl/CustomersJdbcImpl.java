package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Customers;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomersJdbcImpl extends BaseClassJdbcImpl<Customers> implements IBaseRepository<Customers> {

    @Override
    public List<Customers> getAll() {
        String query = "SELECT * FROM customers;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Customers getAllHelper(ResultSet resultSet) throws SQLException {
        Customers customers = new Customers();
        customers.setAssociate(new AssociatesJdbcImpl().getEntityById(resultSet.getInt("associate_id")));
        customers.setCreditScore(resultSet.getString("credit_score"));
        return customers;
    }

    @Override
    public Customers getEntityById(int id) {
        String query = "SELECT * FROM customers WHERE associate_id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Customers getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Customers customers) {
        String query = "INSERT INTO customers (associate_id, credit_score) VALUES ((?), (?))";
        executeStatement(query, "saveEntity", customers);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Customers customers) throws SQLException {
        preparedStatement.setInt(1, customers.getAssociate().getId());
        preparedStatement.setString(2, customers.getCreditScore());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            customers.setAssociate(new AssociatesJdbcImpl().getEntityById(autoIncrementValue));
        }
    }

    @Override
    public void updateEntity(Customers customers) {
        String query = "UPDATE customers SET associate_id = (?), credit_score = (?) WHERE associate_id = (?)";
        executeStatement(query, "updateEntity", customers);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Customers customers) throws SQLException {
        preparedStatement.setInt(1, customers.getAssociate().getId());
        preparedStatement.setString(2, customers.getCreditScore());
        preparedStatement.setInt(3, customers.getAssociate().getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM customers WHERE associate_id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
