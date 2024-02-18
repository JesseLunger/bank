package com.solvd.bank.persistence.jdbcimpl;


import com.solvd.bank.domain.Transaction;
import com.solvd.bank.domain.TransferStatus;
import com.solvd.bank.persistence.ITransferStatusDAO;
import com.solvd.bank.utils.jdbcconnectionutils.ConnectionPool;
import com.solvd.bank.utils.jdbcconnectionutils.MySQLFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransferStatusDAO extends BaseClassDAO<TransferStatus> implements ITransferStatusDAO {

    @Override
    public ArrayList<Transaction> getTransactionsByStatusId(int id) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions " +
                "WHERE status_id = (?);";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(query);) {
            preparedStatement1.setInt(1, id);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                transactions.add(new TransactionDAO().createEntity(resultSet));
            }
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return transactions;
    }

    @Override
    public List<TransferStatus> getAll() {
        String query = "SELECT * FROM transfer_statuses;";
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
        String query = "SELECT * FROM transfer_statuses WHERE id = (?);";
        ArrayList<TransferStatus> transferStatuses = executeStatement(query, "getEntityById", id);
        if (transferStatuses == null || transferStatuses.isEmpty()) {
            return null;
        }
        return transferStatuses.get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(TransferStatus transferStatus) {
        String query = "INSERT INTO transfer_statuses (status) VALUES (?);";
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
        String query = "UPDATE transfer_statuses SET status = (?) WHERE id = (?);";
        executeStatement(query, "updateEntity", transferStatus);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, TransferStatus transferStatus) throws SQLException {
        preparedStatement.setString(1, transferStatus.getStatus());
        preparedStatement.setInt(2, transferStatus.getId());
    }

    @Override
    public void removeEntityById(int id) {
        String query = "DELETE FROM transfer_statuses WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
