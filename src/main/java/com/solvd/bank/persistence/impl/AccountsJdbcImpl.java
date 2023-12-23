package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Accounts;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import com.solvd.bank.persistence.

public class AccountsJdbcImpl extends BaseClassJdbcImpl<Accounts> implements AccountsRepository {

    @Override
    public List<Accounts> getAll() {
        String query = "SELECT * FROM accounts;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Accounts getAllHelper(ResultSet resultSet) throws SQLException {
        Accounts account = new Accounts();
        account.setId(resultSet.getInt("id"));
        account.setBranch(new BranchesJdbcImpl().getEntityById(resultSet.getInt("branch_id")));
        account.setCustomer(new CustomersJdbcImpl().getEntityById(resultSet.getInt("customer_associate_id")));
        account.setAmount(resultSet.getDouble("amount"));
        account.setDateCreated(resultSet.getTimestamp("date_created"));
        account.setHolds(resultSet.getBoolean("holds"));
        return account;
    }

    @Override
    public Accounts getEntityById(int id) {
        String query = "SELECT * FROM accounts WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Accounts getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Accounts account) {
        String query = "INSERT INTO accounts (branch_id, customer_associate_id, amount, date_created, holds) " +
                "VALUES ((?), (?), (?), (?), (?))";
        executeStatement(query, "saveEntity", account);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Accounts account) throws SQLException {
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
    public void updateEntity(Accounts account) {
        String query = "UPDATE accounts SET branch_id = (?), customer_associate_id = (?), " +
                "amount = (?), date_created = (?), holds = (?) WHERE id = (?)";
        executeStatement(query, "updateEntity", account);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Accounts account) throws SQLException {
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
    public void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
