package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.persistence.CustomersRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomersJdbcImpl extends BaseClassJdbcImpl<Customer> implements CustomersRepository {

    @Override
    public List<Customer> getAll() {
        String query = "SELECT * FROM customers;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Customer getAllHelper(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setAssociate(new AssociatesJdbcImpl().getEntityById(resultSet.getInt("associate_id")));
        customer.setCreditScore(resultSet.getString("credit_score"));
        return customer;
    }

    @Override
    public Customer getEntityById(int id) {
        String query = "SELECT * FROM customers WHERE associate_id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Customer getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Customer customer) {
        String query = "INSERT INTO customers (associate_id, credit_score) VALUES ((?), (?))";
        executeStatement(query, "saveEntity", customer);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        preparedStatement.setInt(1, customer.getAssociate().getId());
        preparedStatement.setString(2, customer.getCreditScore());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            customer.setAssociate(new AssociatesJdbcImpl().getEntityById(autoIncrementValue));
        }
    }

    @Override
    public void updateEntity(Customer customer) {
        String query = "UPDATE customers SET associate_id = (?), credit_score = (?) WHERE associate_id = (?)";
        executeStatement(query, "updateEntity", customer);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        preparedStatement.setInt(1, customer.getAssociate().getId());
        preparedStatement.setString(2, customer.getCreditScore());
        preparedStatement.setInt(3, customer.getAssociate().getId());
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
