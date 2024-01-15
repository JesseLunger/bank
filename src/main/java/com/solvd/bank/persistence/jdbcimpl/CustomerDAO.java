package com.solvd.bank.persistence.jdbcimpl;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.persistence.ICustomerDAO;
import com.solvd.bank.utils.jdbcconnectionutils.ConnectionPool;
import com.solvd.bank.utils.jdbcconnectionutils.MySQLFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends BaseClassDAO<Customer> implements ICustomerDAO {

    @Override
    public void updateCreditScore(Customer customer, double newScore) {
        String query = "UPDATE customers " +
                "SET credit_score = (?) " +
                "WHERE associate_id = (?)";
        try (Connection connection = MySQLFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, newScore);
            preparedStatement.setInt(2, customer.getAssociate().getId());
            preparedStatement.executeUpdate();
            customer.setCreditScore(newScore);
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Customer> getAll() {
        String query = "SELECT * FROM customers;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Customer createEntity(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setAssociate(new AssociateDAO().getEntityById(resultSet.getInt("associate_id")));
        customer.setCreditScore(resultSet.getDouble("credit_score"));
        return customer;
    }

    @Override
    public Customer getEntityById(int id) {
        String query = "SELECT * FROM customers " +
                "WHERE associate_id = (?);";
        ArrayList<Customer> customers = executeStatement(query, "getEntityById", id);
        if (customers == null || customers.isEmpty()) {
            return null;
        }
        return customers.get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(Customer customer) {
        String query = "INSERT INTO customers (associate_id, credit_score) " +
                "VALUES ((?), (?))";
        executeStatement(query, "saveEntity", customer);
        Integer autoIncrementValue = getAutoIncrementValue();
        if (autoIncrementValue != null) {
            customer.setAssociate(new AssociateDAO().getEntityById(autoIncrementValue));
        }
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        preparedStatement.setInt(1, customer.getAssociate().getId());
        preparedStatement.setDouble(2, customer.getCreditScore());
    }

    @Override
    public void updateEntity(Customer customer) {
        String query = "UPDATE customers SET associate_id = (?), credit_score = (?) " +
                "WHERE associate_id = (?)";
        executeStatement(query, "updateEntity", customer);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        preparedStatement.setInt(1, customer.getAssociate().getId());
        preparedStatement.setDouble(2, customer.getCreditScore());
        preparedStatement.setInt(3, customer.getAssociate().getId());
    }

    @Override
    public void removeEntityById(int id) {
        String query = "DELETE FROM customers " +
                "WHERE associate_id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
