package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Account;
import com.solvd.bank.utils.ConnectionPool;

import java.sql.*;
import java.util.List;

public class AccountDAO extends BaseClassDAO<Account> implements com.solvd.bank.persistence.IAccountDAO {

    @Override
    public void addAmount(Account account, double amountToAdd) {
        String selectQuery = "SELECT amount FROM accounts WHERE id = (?)";
        String updateQuery = "UPDATE accounts SET amount = (?) WHERE id = (?)";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
             PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {

            selectStatement.setInt(1, account.getId());
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    double newAmount = resultSet.getDouble("amount") + amountToAdd;
                    updateStatement.setDouble(1, newAmount);
                    updateStatement.setInt(2, account.getId());
                    updateStatement.executeUpdate();
                    account.setAmount(newAmount);
                }
            }
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }


    @Override
    public List<Account> getAll() {
        String query = "SELECT * FROM accounts;";
        return executeStatement(query, "getAll");
    }

    @Override
    protected Account createEntity(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setBranch(new BranchDAO().getEntityById(resultSet.getInt("branch_id")));
        account.setCustomer(new CustomerDAO().getEntityById(resultSet.getInt("customer_associate_id")));
        account.setAmount(resultSet.getDouble("amount"));
        account.setDateCreated(resultSet.getTimestamp("date_created"));
        account.setHolds(resultSet.getBoolean("holds"));
        return account;
    }

    @Override
    public Account getEntityById(int id) {
        String query = "SELECT * FROM accounts WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    protected Account prepareCreateSingleEntityStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return getResultsFromStatement(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Account account) {
        String query = "INSERT INTO accounts (branch_id, customer_associate_id, amount, date_created, holds) " +
                "VALUES ((?), (?), (?), (?), (?))";
        executeStatement(query, "saveEntity", account);
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Account account) throws SQLException {
        preparedStatement.setInt(1, account.getBranch().getId());
        preparedStatement.setInt(2, account.getCustomer().getAssociate().getId());
        preparedStatement.setDouble(3, account.getAmount());
        preparedStatement.setTimestamp(4, account.getDateCreated());
        preparedStatement.setBoolean(5, account.isHolds());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            account.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(Account account) {
        String query = "UPDATE accounts SET branch_id = (?), customer_associate_id = (?), " +
                "amount = (?), date_created = (?), holds = (?) WHERE id = (?)";
        executeStatement(query, "updateEntity", account);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Account account) throws SQLException {
        preparedStatement.setInt(1, account.getBranch().getId());
        preparedStatement.setInt(2, account.getCustomer().getAssociate().getId());
        preparedStatement.setDouble(3, account.getAmount());
        preparedStatement.setTimestamp(4, account.getDateCreated());
        preparedStatement.setBoolean(5, account.isHolds());
        preparedStatement.setInt(6, account.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM accounts WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
