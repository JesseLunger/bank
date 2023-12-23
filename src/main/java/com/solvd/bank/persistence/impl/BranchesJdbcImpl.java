package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Branches;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BranchesJdbcImpl extends BaseClassJdbcImpl<Branches> implements IBaseRepository<Branches> {

    @Override
    public List<Branches> getAll() {
        String query = "SELECT * FROM branches;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Branches getAllHelper(ResultSet resultSet) throws SQLException {
        Branches branch = new Branches();
        branch.setId(resultSet.getInt("id"));
        branch.setLocation(new LocationsJdbcImpl().getEntityById(resultSet.getInt("location_id")));
        branch.setBranchName(resultSet.getString("branch_name"));
        return branch;
    }

    @Override
    public Branches getEntityById(int id) {
        String query = "SELECT * FROM branches WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Branches getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Branches branch) {
        String query = "INSERT INTO branches (location_id, branch_name) VALUES ((?), (?))";
        executeStatement(query, "saveEntity", branch);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Branches branch) throws SQLException {
        preparedStatement.setInt(1, branch.getLocation().getId());
        preparedStatement.setString(2, branch.getBranchName());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            branch.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(Branches branch) {
        String query = "UPDATE branches SET location_id = (?), branch_name = (?) WHERE id = (?)";
        executeStatement(query, "updateEntity", branch);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Branches branch) throws SQLException {
        preparedStatement.setInt(1, branch.getLocation().getId());
        preparedStatement.setString(2, branch.getBranchName());
        preparedStatement.setInt(3, branch.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM branches WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
