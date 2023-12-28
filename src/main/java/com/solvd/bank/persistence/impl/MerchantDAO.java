package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.domain.Merchant;
import com.solvd.bank.persistence.IMerchantDAO;
import com.solvd.bank.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MerchantDAO extends BaseClassDAO<Merchant> implements IMerchantDAO {

    @Override
    public ArrayList<Customer> getCustomersWithTransactions(Merchant merchant) {
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT cus.* FROM customers cus" +
                "LEFT JOIN accounts acc ON cus.associate_id = acc.customer_id" +
                "LEFT JOIN cards car ON acc.id = car.account_id" +
                "LEFT JOIN transactions trans ON car.id = trans.card_id" +
                "RIGHT JOIN merchants mer ON trans.merchant_id = mer.associate_id" +
                "WHERE mer.id = (?);";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, merchant.getAssociate().getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                CustomerDAO customersDAO = new CustomerDAO();
                while (resultSet.next()) {
                    customers.add(customersDAO.createEntity(resultSet));
                }
            }
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return customers;
    }

    @Override
    public List<Merchant> getAll() {
        String query = "SELECT * FROM merchants;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Merchant createEntity(ResultSet resultSet) throws SQLException {
        Merchant merchant = new Merchant();
        merchant.setAssociate(new AssociateDAO().getEntityById(resultSet.getInt("associate_id")));
        return merchant;
    }

    @Override
    public Merchant getEntityById(int id) {
        String query = "SELECT * FROM merchants WHERE associate_id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(Merchant merchant) {
        String query = "INSERT INTO merchants (associate_id) VALUES ((?))";
        executeStatement(query, "saveEntity", merchant);
        Integer autoIncrementValue = getAutoIncrementValue();
        if (autoIncrementValue != null) {
            merchant.setAssociate(new AssociateDAO().getEntityById(autoIncrementValue));
        }
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Merchant merchant) throws SQLException {
        preparedStatement.setInt(1, merchant.getAssociate().getId());
    }

    @Override
    public void updateEntity(Merchant merchant) {
        String query = "UPDATE merchants SET associate_id = (?) WHERE associate_id = (?)";
        executeStatement(query, "updateEntity", merchant);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Merchant merchant) throws SQLException {
        preparedStatement.setInt(1, merchant.getAssociate().getId());
        preparedStatement.setInt(2, merchant.getAssociate().getId());
    }

    @Override
    public void removeEntityById(int id) {
        String query = "DELETE FROM merchants WHERE associate_id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
