package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Transactions;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class TransactionsJdbcImpl extends BaseClassJdbcImpl<Transactions> implements IBaseRepository<Transactions> {

    @Override
    public List<Transactions> getAll() {
        String query = "SELECT * FROM transactions;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Transactions getAllHelper(ResultSet resultSet) throws SQLException {
        Transactions transaction = new Transactions();
        transaction.setId(resultSet.getInt("id"));
        transaction.setCard(new CardsJdbcImpl().getEntityById(resultSet.getInt("card_id")));
        transaction.setMerchant(new MerchantsJdbcImpl().getEntityById(resultSet.getInt("merchant_id")));
        transaction.setStatusId(resultSet.getInt("status_id"));
        transaction.setTime(resultSet.getTimestamp("time"));
        transaction.setAmount(resultSet.getDouble("amount"));
        return transaction;
    }

    @Override
    public Transactions getEntityById(int id) {
        String query = "SELECT * FROM transactions WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Transactions getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Transactions transaction) {
        String query = "INSERT INTO transactions (card_id, merchant_id, status_id, time, amount) " +
                "VALUES ((?), (?), (?), (?), (?))";
        executeStatement(query, "saveEntity", transaction);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Transactions transaction) throws SQLException {
        preparedStatement.setInt(1, transaction.getCard().getId());
        preparedStatement.setInt(2, transaction.getMerchant().getAssociate().getId());
        preparedStatement.setInt(3, transaction.getStatusId());
        preparedStatement.setTimestamp(4, transaction.getTime());
        preparedStatement.setDouble(5, transaction.getAmount());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            transaction.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(Transactions transaction) {
        String query = "UPDATE transactions SET card_id = (?), merchant_id = (?), " +
                "status_id = (?), time = (?), amount = (?) WHERE id = (?);";
        executeStatement(query, "updateEntity", transaction);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Transactions transaction) throws SQLException {
        preparedStatement.setInt(1, transaction.getCard().getId());
        preparedStatement.setInt(2, transaction.getMerchant().getAssociate().getId());
        preparedStatement.setInt(3, transaction.getStatusId());
        preparedStatement.setTimestamp(4, transaction.getTime());
        preparedStatement.setDouble(5, transaction.getAmount());
        preparedStatement.setInt(6, transaction.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM transactions WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
