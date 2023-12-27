package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.persistence.ICustomersDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO extends BaseClassDAO<Customer> implements ICustomersDAO {

    @Override
    public List<Customer> getAll() {
        String query = "SELECT * FROM customers;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Customer createEntity(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setAssociate(new AssociateDAO().getEntityById(resultSet.getInt("associate_id")));
        customer.setCreditScore(resultSet.getString("credit_score"));
        return customer;
    }

    @Override
    public Customer getEntityById(int id) {
        String query = "SELECT * FROM customers WHERE associate_id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(Customer customer) {
        String query = "INSERT INTO customers (associate_id, credit_score) VALUES ((?), (?))";
        executeStatement(query, "saveEntity", customer);
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        preparedStatement.setInt(1, customer.getAssociate().getId());
        preparedStatement.setString(2, customer.getCreditScore());

        Integer autoIncrementValue = getAutoIncrementValue();
        if (autoIncrementValue != null) {
            customer.setAssociate(new AssociateDAO().getEntityById(autoIncrementValue));
        }
    }

    @Override
    public void updateEntity(Customer customer) {
        String query = "UPDATE customers SET associate_id = (?), credit_score = (?) WHERE associate_id = (?)";
        executeStatement(query, "updateEntity", customer);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        preparedStatement.setInt(1, customer.getAssociate().getId());
        preparedStatement.setString(2, customer.getCreditScore());
        preparedStatement.setInt(3, customer.getAssociate().getId());
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM customers WHERE associate_id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}