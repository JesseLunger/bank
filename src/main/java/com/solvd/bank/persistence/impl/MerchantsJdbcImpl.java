package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Merchants;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MerchantsJdbcImpl extends BaseClassJdbcImpl<Merchants> implements IBaseRepository<Merchants> {

    @Override
    public List<Merchants> getAll() {
        String query = "SELECT * FROM merchants;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Merchants getAllHelper(ResultSet resultSet) throws SQLException {
        Merchants merchants = new Merchants();
        merchants.setAssociate(new AssociatesJdbcImpl().getEntityById(resultSet.getInt("associate_id")));
        return merchants;
    }

    @Override
    public Merchants getEntityById(int id) {
        String query = "SELECT * FROM merchants WHERE associate_id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Merchants getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Merchants merchants) {
        String query = "INSERT INTO merchants (associate_id) VALUES ((?))";
        executeStatement(query, "saveEntity", merchants);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Merchants merchants) throws SQLException {
        preparedStatement.setInt(1, merchants.getAssociate().getId());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            merchants.setAssociate(new AssociatesJdbcImpl().getEntityById(autoIncrementValue));
        }
    }

    @Override
    public void updateEntity(Merchants merchants) {
        String query = "UPDATE merchants SET associate_id = (?) WHERE associate_id = (?)";
        executeStatement(query, "updateEntity", merchants);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Merchants merchants) throws SQLException {
        preparedStatement.setInt(1, merchants.getAssociate().getId());
        preparedStatement.setInt(2, merchants.getAssociate().getId());
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
