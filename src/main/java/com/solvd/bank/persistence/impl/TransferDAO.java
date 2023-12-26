package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Transfer;
import com.solvd.bank.persistence.ITransferDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransferDAO extends BaseClassDAO<Transfer> implements ITransferDAO {

    @Override
    public List<Transfer> getAll() {
        String query = "SELECT * FROM transfers;";
        return executeStatement(query, "getAll");
    }

    @Override
    protected Transfer createEntity(ResultSet resultSet) throws SQLException {
        Transfer transfer = new Transfer();
        transfer.setId(resultSet.getInt("id"));
        transfer.setSender(new AccountDAO().getEntityById(resultSet.getInt("sender_account_id")));
        transfer.setReceiver(new AccountDAO().getEntityById(resultSet.getInt("receiver_account_id")));
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
    protected Transfer prepareCreateSingleEntityStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return getResultsFromStatement(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Transfer transfer) {
        String query = "INSERT INTO transfers (sender_account_id, receiver_account_id, status_id, transfer_time, amount) " +
                "VALUES ((?), (?), (?), (?), (?))";
        executeStatement(query, "saveEntity", transfer);
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Transfer transfer) throws SQLException {
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
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Transfer transfer) throws SQLException {
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
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
