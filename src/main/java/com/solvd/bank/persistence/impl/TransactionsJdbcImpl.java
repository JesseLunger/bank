package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.persistence.TransactionsRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransactionsJdbcImpl extends BaseClassJdbcImpl<Transaction> implements TransactionsRepository {

    @Override
    public List<Transaction> getAll() {
        String query = "SELECT * FROM transactions;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Transaction getAllHelper(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getInt("id"));
        transaction.setCard(new CardsJdbcImpl().getEntityById(resultSet.getInt("card_id")));
        transaction.setMerchant(new MerchantsJdbcImpl().getEntityById(resultSet.getInt("merchant_id")));
        transaction.setStatusId(resultSet.getInt("status_id"));
        transaction.setTime(resultSet.getTimestamp("time"));
        transaction.setAmount(resultSet.getDouble("amount"));
        return transaction;
    }

    @Override
    public Transaction getEntityById(int id) {
        String query = "SELECT * FROM transactions WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Transaction getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Transaction transaction) {
        String query = "INSERT INTO transactions (card_id, merchant_id, status_id, time, amount) " +
                "VALUES ((?), (?), (?), (?), (?))";
        executeStatement(query, "saveEntity", transaction);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Transaction transaction) throws SQLException {
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
    public void updateEntity(Transaction transaction) {
        String query = "UPDATE transactions SET card_id = (?), merchant_id = (?), " +
                "status_id = (?), time = (?), amount = (?) WHERE id = (?);";
        executeStatement(query, "updateEntity", transaction);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Transaction transaction) throws SQLException {
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
