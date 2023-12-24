package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.persistence.Branchesrepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BranchesJdbcImpl extends BaseClassJdbcImpl<Branch> implements Branchesrepository {

    @Override
    public List<Branch> getAll() {
        String query = "SELECT * FROM branches;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Branch getAllHelper(ResultSet resultSet) throws SQLException {
        Branch branch = new Branch();
        branch.setId(resultSet.getInt("id"));
        branch.setLocation(new LocationsJdbcImpl().getEntityById(resultSet.getInt("location_id")));
        branch.setBranchName(resultSet.getString("branch_name"));
        return branch;
    }

    @Override
    public Branch getEntityById(int id) {
        String query = "SELECT * FROM branches WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Branch getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Branch branch) {
        String query = "INSERT INTO branches (location_id, branch_name) VALUES ((?), (?))";
        executeStatement(query, "saveEntity", branch);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Branch branch) throws SQLException {
        preparedStatement.setInt(1, branch.getLocation().getId());
        preparedStatement.setString(2, branch.getBranchName());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            branch.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(Branch branch) {
        String query = "UPDATE branches SET location_id = (?), branch_name = (?) WHERE id = (?)";
        executeStatement(query, "updateEntity", branch);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Branch branch) throws SQLException {
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
