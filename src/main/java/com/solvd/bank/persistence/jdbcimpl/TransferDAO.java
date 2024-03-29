package com.solvd.bank.persistence.jdbcimpl;

import com.solvd.bank.domain.Transfer;
import com.solvd.bank.persistence.ITransferDAO;
import com.solvd.bank.utils.enums.StatusNames;
import com.solvd.bank.utils.jdbcconnectionutils.ConnectionPool;
import com.solvd.bank.utils.jdbcconnectionutils.MySQLFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransferDAO extends BaseClassDAO<Transfer> implements ITransferDAO {

    @Override
    public void removeDeclinedTransfers() {
        String query1 = "SELECT id FROM transfer_statuses " +
                "WHERE status = (?)";
        String query2 = "DELETE FROM transfers " +
                "WHERE status_id = (?)";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
             PreparedStatement preparedStatement2 = connection.prepareStatement(query2);) {
            preparedStatement1.setString(1, StatusNames.DECLINED.getStatus());
            ResultSet resultSet = preparedStatement1.executeQuery();
            if (resultSet.next()) {
                int declined_status_id = resultSet.getInt("id");
                preparedStatement2.setInt(1, declined_status_id);
                preparedStatement2.executeUpdate();
            }
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Transfer> getAll() {
        String query = "SELECT * FROM transfers;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Transfer createEntity(ResultSet resultSet) throws SQLException {
        Transfer transfer = new Transfer();
        transfer.setId(resultSet.getInt("id"));
        transfer.setSender(new AccountDAO().getEntityById(resultSet.getInt("sender_id")));
        transfer.setReceiver(new AccountDAO().getEntityById(resultSet.getInt("receiver_id")));
        transfer.setTransferStatus(new TransferStatusDAO().getEntityById(resultSet.getInt("status_id")));
        transfer.setTransferTime(resultSet.getTimestamp("transfer_time"));
        transfer.setAmount(resultSet.getDouble("amount"));
        return transfer;
    }

    @Override
    public Transfer getEntityById(int id) {
        String query = "SELECT * FROM transfers " +
                "WHERE id = (?);";
        ArrayList<Transfer> transfers = executeStatement(query, "getEntityById", id);
        if (transfers == null || transfers.isEmpty()) {
            return null;
        }
        return transfers.get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(Transfer transfer) {
        String query = "INSERT INTO transfers (sender_id, receiver_id, status_id, transfer_time, amount) " +
                "VALUES ((?), (?), (?), (?), (?))";
        executeStatement(query, "saveEntity", transfer);
        Integer autoIncrementValue = getAutoIncrementValue();
        if (autoIncrementValue != null) {
            transfer.setId(autoIncrementValue);
        }
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Transfer transfer) throws SQLException {
        preparedStatement.setInt(1, transfer.getSender().getId());
        preparedStatement.setInt(2, transfer.getReceiver().getId());
        preparedStatement.setInt(3, transfer.getTransferStatus().getId());
        preparedStatement.setTimestamp(4, transfer.getTransferTime());
        preparedStatement.setDouble(5, transfer.getAmount());
    }

    @Override
    public void updateEntity(Transfer transfer) {
        String query = "UPDATE transfers SET sender_id = (?), receiver_id = (?), " +
                "status_id = (?), transfer_time = (?), amount = (?) WHERE id = (?);";
        executeStatement(query, "updateEntity", transfer);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Transfer transfer) throws SQLException {
        preparedStatement.setInt(1, transfer.getSender().getId());
        preparedStatement.setInt(2, transfer.getReceiver().getId());
        preparedStatement.setInt(3, transfer.getTransferStatus().getId());
        preparedStatement.setTimestamp(4, transfer.getTransferTime());
        preparedStatement.setDouble(5, transfer.getAmount());
        preparedStatement.setInt(6, transfer.getId());
    }

    @Override
    public void removeEntityById(int id) {
        String query = "DELETE FROM transfers " +
                "WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
