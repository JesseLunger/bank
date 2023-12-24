package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Merchant;
import com.solvd.bank.persistence.MerchantsRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MerchantsJdbcImpl extends BaseClassJdbcImpl<Merchant> implements MerchantsRepository {

    @Override
    public List<Merchant> getAll() {
        String query = "SELECT * FROM merchants;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Merchant getAllHelper(ResultSet resultSet) throws SQLException {
        Merchant merchant = new Merchant();
        merchant.setAssociate(new AssociatesJdbcImpl().getEntityById(resultSet.getInt("associate_id")));
        return merchant;
    }

    @Override
    public Merchant getEntityById(int id) {
        String query = "SELECT * FROM merchants WHERE associate_id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Merchant getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Merchant merchant) {
        String query = "INSERT INTO merchants (associate_id) VALUES ((?))";
        executeStatement(query, "saveEntity", merchant);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Merchant merchant) throws SQLException {
        preparedStatement.setInt(1, merchant.getAssociate().getId());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            merchant.setAssociate(new AssociatesJdbcImpl().getEntityById(autoIncrementValue));
        }
    }

    @Override
    public void updateEntity(Merchant merchant) {
        String query = "UPDATE merchants SET associate_id = (?) WHERE associate_id = (?)";
        executeStatement(query, "updateEntity", merchant);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Merchant merchant) throws SQLException {
        preparedStatement.setInt(1, merchant.getAssociate().getId());
        preparedStatement.setInt(2, merchant.getAssociate().getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM merchants WHERE associate_id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
