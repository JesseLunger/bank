package com.solved.bank.persistence.impl;

import com.solved.bank.domain.Associates;
import com.solved.bank.domain.Merchants;
import com.solved.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MerchantsJdbcImpl extends BaseClassJdbcImpl<Merchants> implements IBaseRepository<Merchants> {

    @Override
    protected Merchants getAllHelper(ResultSet resultSet) {
        Merchants merchants = new Merchants();
        try {
            Associates associate = new AssociatesJdbcImpl().getEntityById(resultSet.getInt("associate_id"));
            merchants.setAssociate(associate);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return merchants;
    }

    @Override
    protected Merchants getEntityByIdHelper(PreparedStatement preparedStatement, int id) {
        try {
            preparedStatement.setInt(1, id);
            return processResultSet(preparedStatement).get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    protected void saveEntityHelper(PreparedStatement preparedStatement, Merchants merchants) {
        try {
            preparedStatement.setInt(1, merchants.getAssociate().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    protected void updateEntityHelper(PreparedStatement preparedStatement, Merchants merchants) {
        try {
            preparedStatement.setInt(1, merchants.getAssociate().getId());
            preparedStatement.setInt(2, merchants.getAssociate().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    protected void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) {
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void saveEntity(Merchants merchants) {
        String query = "INSERT INTO merchants (associate_id) VALUES (?)";
        executeStatement(query, "saveEntity", merchants);
    }

    @Override
    public Merchants getEntityById(int id) {
        String query = "SELECT * FROM merchants WHERE associate_id = ?";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public void updateEntity(Merchants merchants) {
        String query = "UPDATE merchants SET associate_id = ? WHERE associate_id = ?";
        executeStatement(query, "updateEntity", merchants);
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM merchants WHERE associate_id = ?";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public List<Merchants> getAll() {
        String query = "SELECT * FROM merchants";
        return executeStatement(query, "getAll");
    }
}
