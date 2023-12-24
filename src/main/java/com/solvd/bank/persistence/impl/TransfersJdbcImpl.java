package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Transfer;
import com.solvd.bank.persistence.TransfersRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransfersJdbcImpl extends BaseClassJdbcImpl<Transfer> implements TransfersRepository {

    @Override
    public List<Transfer> getAll() {
        String query = "SELECT * FROM transfers;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Transfer getAllHelper(ResultSet resultSet) throws SQLException {
        Transfer transfer = new Transfer();
        transfer.setId(resultSet.getInt("id"));
        transfer.setSender(new AccountsJdbcImpl().getEntityById(resultSet.getInt("sender_account_id")));
        transfer.setReceiver(new AccountsJdbcImpl().getEntityById(resultSet.getInt("receiver_account_id")));
        transfer.setStatusId(resultSet.getInt("status_id"));
        transfer.setTime(resultSet.getTimestamp("transfer_time"));
        transfer.setAmount(resultSet.getDouble("amount"));
        return transfer;
    }

    @Override
    public Transfer getEntityById(int id) {
        String query = "SELECT * FROM transfers WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Transfer getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Transfer transfer) {
        String query = "INSERT INTO transfers (sender_account_id, receiver_account_id, status_id, transfer_time, amount) " +
                "VALUES ((?), (?), (?), (?), (?))";
        executeStatement(query, "saveEntity", transfer);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Transfer transfer) throws SQLException {
        preparedStatement.setInt(1, transfer.getSender().getId());
        preparedStatement.setInt(2, transfer.getReceiver().getId());
        preparedStatement.setInt(3, transfer.getStatusId());
        preparedStatement.setTimestamp(4, transfer.getTime());
        preparedStatement.setDouble(5, transfer.getAmount());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            transfer.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(Transfer transfer) {
        String query = "UPDATE transfers SET sender_account_id = (?), receiver_account_id = (?), " +
                "status_id = (?), transfer_time = (?), amount = (?) WHERE id = (?);";
        executeStatement(query, "updateEntity", transfer);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Transfer transfer) throws SQLException {
        preparedStatement.setInt(1, transfer.getSender().getId());
        preparedStatement.setInt(2, transfer.getReceiver().getId());
        preparedStatement.setInt(3, transfer.getStatusId());
        preparedStatement.setTimestamp(4, transfer.getTime());
        preparedStatement.setDouble(5, transfer.getAmount());
        preparedStatement.setInt(6, transfer.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM transfers WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}