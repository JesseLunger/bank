package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.domain.TransferStatus;
import com.solvd.bank.persistence.ITransactionDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO extends BaseClassDAO<Transaction> implements ITransactionDAO {

    @Override
    public void updateStatus(Transaction transaction, TransferStatus transferStatus) {
        transaction.setTransferStatus(transferStatus);
        updateEntity(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        String query = "SELECT * FROM transactions;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Transaction createEntity(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getInt("id"));
        transaction.setCard(new CardDAO().getEntityById(resultSet.getInt("card_id")));
        transaction.setMerchant(new MerchantDAO().getEntityById(resultSet.getInt("merchant_id")));
        transaction.setTransferStatus(new TransferStatusDAO().getEntityById(resultSet.getInt("status_id")));
        transaction.setTime(resultSet.getTimestamp("transaction_time"));
        transaction.setAmount(resultSet.getDouble("amount"));
        return transaction;
    }

    @Override
    public Transaction getEntityById(int id) {
        String query = "SELECT * FROM transactions WHERE id = (?);";
        ArrayList<Transaction> transactions = executeStatement(query, "getEntityById", id);
        if (transactions == null || transactions.isEmpty()) {
            return null;
        }
        return transactions.get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(Transaction transaction) {
        String query = "INSERT INTO transactions (card_id, merchant_id, status_id, transaction_time, amount) " +
                "VALUES ((?), (?), (?), (?), (?))";
        executeStatement(query, "saveEntity", transaction);
        Integer autoIncrementValue = getAutoIncrementValue();
        if (autoIncrementValue != null) {
            transaction.setId(autoIncrementValue);
        }
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Transaction transaction) throws SQLException {
        preparedStatement.setInt(1, transaction.getCard().getId());
        preparedStatement.setInt(2, transaction.getMerchant().getAssociate().getId());
        preparedStatement.setInt(3, transaction.getTransferStatus().getId());
        preparedStatement.setTimestamp(4, transaction.getTime());
        preparedStatement.setDouble(5, transaction.getAmount());
    }

    @Override
    public void updateEntity(Transaction transaction) {
        String query = "UPDATE transactions SET card_id = (?), merchant_id = (?), " +
                "status_id = (?), transaction_time = (?), amount = (?) WHERE id = (?);";
        executeStatement(query, "updateEntity", transaction);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Transaction transaction) throws SQLException {
        preparedStatement.setInt(1, transaction.getCard().getId());
        preparedStatement.setInt(2, transaction.getMerchant().getAssociate().getId());
        preparedStatement.setInt(3, transaction.getTransferStatus().getId());
        preparedStatement.setTimestamp(4, transaction.getTime());
        preparedStatement.setDouble(5, transaction.getAmount());
        preparedStatement.setInt(6, transaction.getId());
    }

    @Override
    public void removeEntityById(int id) {
        String query = "DELETE FROM transactions WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
