package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Accounts;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountsJdbcImpl extends BaseClassJdbcImpl<Accounts> implements IBaseRepository<Accounts> {

    @Override
    protected Accounts getAllHelper(ResultSet resultSet) throws SQLException{
        Accounts account = new Accounts();
        account.setId(resultSet.getInt("id"));
        account.setBranch(new BranchesJdbcImpl().getEntityById(resultSet.getInt("branch_id")));
        account.setCustomer(new CustomersJdbcImpl().getEntityById(resultSet.getInt("customer_id")));
        account.setAmount(resultSet.getDouble("amount"));
        account.setDateCreated(resultSet.getTimestamp("date_created"));
        account.setHolds(resultSet.getBoolean("holds"));
        return account;
    }

    @Override
    protected Accounts getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    protected void saveEntityHelper(PreparedStatement preparedStatement, Accounts account) throws SQLException {
        preparedStatement.setInt(1, account.getBranch().getId());
        preparedStatement.setInt(2, account.getCustomer().getAssociate().getId());
        preparedStatement.setDouble(3, account.getAmount());
        preparedStatement.setTimestamp(4, account.getDateCreated());
        preparedStatement.setBoolean(5, account.isHolds());
        int AICheck = preparedStatement.executeUpdate();
        if (AICheck > 0){
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                account.setId(generatedKeys.getInt(1));
            }
        }
    }

    @Override
    protected void updateEntityHelper(PreparedStatement preparedStatement, Accounts account) throws SQLException{
        int counter = 1;
        for (Accounts.AccountsColumns column : Accounts.AccountsColumns.values()) {
            parseEnum(counter, column.getColumnType(), account.getColumnValue(column), preparedStatement);
            counter++;
        }
        preparedStatement.setInt(counter, account.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    protected void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public void saveEntity(Accounts account) {
        String query = "INSERT INTO accounts (branch_id, customer_id, amount, date_created, holds) VALUES (?, ?, ?, ?, ?)";
        executeStatement(query, "saveEntity", account);
    }

    @Override
    public Accounts getEntityById(int id) {
        String query = "SELECT * FROM accounts WHERE id = ?";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public void updateEntity(Accounts account) {
        String query = "UPDATE accounts SET branch_id = ?, customer_id = ?, amount = ?, date_created = ?, holds = ? WHERE id = ?";
        executeStatement(query, "updateEntity", account);
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM accounts WHERE id = ?";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public List<Accounts> getAll() {
        String query = "SELECT * FROM accounts";
        return executeStatement(query, "getAll");
    }
}
