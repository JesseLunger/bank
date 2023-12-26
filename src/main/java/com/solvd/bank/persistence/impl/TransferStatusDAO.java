package com.solvd.bank.persistence.impl;


import com.solvd.bank.domain.TransferStatus;
import com.solvd.bank.persistence.ITransferStatusDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransferStatusDAO extends BaseClassDAO<TransferStatus> implements ITransferStatusDAO {

    @Override
    public List<TransferStatus> getAll() {
        String query = "SELECT * FROM transfer_status;";
        return executeStatement(query, "getAll");
    }

    @Override
    protected TransferStatus createEntity(ResultSet resultSet) throws SQLException {
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
    protected TransferStatus prepareCreateSingleEntityStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return getResultsFromStatement(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(TransferStatus transferStatus) {
        String query = "INSERT INTO transfer_status (status) VALUES (?);";
        executeStatement(query, "saveEntity", transferStatus);
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, TransferStatus transferStatus) throws SQLException {
        preparedStatement.setString(1, transferStatus.getStatus());
        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            transferStatus.setId(autoIncrementValue);
        }
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
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM transfer_status WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
