package com.solvd.bank.persistence.impl;


import com.solvd.bank.domain.Transaction;
import com.solvd.bank.domain.TransferStatus;
import com.solvd.bank.persistence.ITransferStatusDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransferStatusDAO extends BaseClassDAO<TransferStatus> implements ITransferStatusDAO {

    @Override
    public ArrayList<Transaction> getTransactionsByStatusId(int id) {
        ArrayList<Transaction> transactions = new ArrayList<>(new TransactionDAO().getAll());
        ArrayList<Transaction> acceptedTransactions = transactions.stream()
                .filter(transaction -> "accepted".equals(transaction.getTransferStatus().getStatus()))
                .collect(Collectors.toCollection(ArrayList::new));
        return acceptedTransactions;
    }


    @Override
    public List<TransferStatus> getAll() {
        String query = "SELECT * FROM transfer_status;";
        return executeStatement(query, "getAll");
    }

    @Override
    public TransferStatus createEntity(ResultSet resultSet) throws SQLException {
        TransferStatus transferStatus = new TransferStatus();
        transferStatus.setId(resultSet.getInt("id"));
        transferStatus.setStatus(resultSet.getString("status"));
        return transferStatus;
    }

    @Override
    public TransferStatus getEntityById(int id) {
        String query = "SELECT * FROM transfer_status WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(TransferStatus transferStatus) {
        String query = "INSERT INTO transfer_status (status) VALUES (?);";
        executeStatement(query, "saveEntity", transferStatus);
        Integer autoIncrementValue = getAutoIncrementValue();
        if (autoIncrementValue != null) {
            transferStatus.setId(autoIncrementValue);
        }
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, TransferStatus transferStatus) throws SQLException {
        preparedStatement.setString(1, transferStatus.getStatus());
    }

    @Override
    public void updateEntity(TransferStatus transferStatus) {
        String query = "UPDATE transfer_status SET status = (?) WHERE id = (?);";
        executeStatement(query, "updateEntity", transferStatus);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, TransferStatus transferStatus) throws SQLException {
        preparedStatement.setString(1, transferStatus.getStatus());
        preparedStatement.setInt(2, transferStatus.getId());
    }

    @Override
    public void removeEntityById(int id) {
        String query = "DELETE FROM transfer_status WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
