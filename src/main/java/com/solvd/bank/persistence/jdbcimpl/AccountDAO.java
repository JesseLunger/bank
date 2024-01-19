package com.solvd.bank.persistence.jdbcimpl;

import com.solvd.bank.domain.Account;
import com.solvd.bank.utils.jdbcconnectionutils.ConnectionPool;
import com.solvd.bank.utils.jdbcconnectionutils.MySQLFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends BaseClassDAO<Account> implements com.solvd.bank.persistence.IAccountDAO {

    @Override
    public void addAmount(Account account, double amountToAdd) {
        String query = "UPDATE accounts " +
                "SET amount = (?) " +
                "WHERE id = (?)";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            double newAmount = amountToAdd + account.getAmount();
            preparedStatement.setDouble(1, newAmount);
            preparedStatement.setInt(2, account.getId());
            preparedStatement.executeUpdate();
            account.setAmount(newAmount);
        } catch (SQLException | InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Account> getAll() {
        String query = "SELECT * FROM accounts;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Account createEntity(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setBranch(new BranchDAO().getEntityById(resultSet.getInt("branch_id")));
        account.setCustomer(new CustomerDAO().getEntityById(resultSet.getInt("customer_id")));
        account.setAmount(resultSet.getDouble("amount"));
        account.setDateCreated(resultSet.getTimestamp("date_created"));
        account.setHolds(resultSet.getBoolean("holds"));
        return account;
    }

    @Override
    public Account getEntityById(int id) {
        String query = "SELECT * FROM accounts " +
                "WHERE id = (?);";
        ArrayList<Account> accounts = executeStatement(query, "getEntityById", id);
        if (accounts == null || accounts.isEmpty()) {
            return null;
        }
        return accounts.get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(Account account) {
        String query = "INSERT INTO accounts (branch_id, customer_id, amount, date_created, holds) " +
                "VALUES ((?), (?), (?), (?), (?))";
        executeStatement(query, "saveEntity", account);
        Integer autoIncrementValue = getAutoIncrementValue();
        if (autoIncrementValue != null) {
            account.setId(autoIncrementValue);
        }
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Account account) throws SQLException {
        preparedStatement.setInt(1, account.getBranch().getId());
        preparedStatement.setInt(2, account.getCustomer().getAssociate().getId());
        preparedStatement.setDouble(3, account.getAmount());
        preparedStatement.setTimestamp(4, account.getDateCreated());
        preparedStatement.setBoolean(5, account.isHolds());
    }

    @Override
    public void updateEntity(Account account) {
        String query = "UPDATE accounts SET branch_id = (?), customer_id = (?), " +
                "amount = (?), date_created = (?), holds = (?) " +
                "WHERE id = (?);";
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
    }

    @Override
    public void removeEntityById(int id) {
        String query = "DELETE FROM accounts " +
                "WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
