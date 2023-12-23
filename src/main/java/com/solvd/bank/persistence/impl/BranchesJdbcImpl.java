package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Branches;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BranchesJdbcImpl extends BaseClassJdbcImpl<Branches> implements IBaseRepository<Branches> {

    @Override
    protected Branches getAllHelper(ResultSet resultSet) {
        Branches branch = new Branches();
        try {
            branch.setId(resultSet.getInt("id"));
            branch.setLocation(new LocationsJdbcImpl().getEntityById(resultSet.getInt("location_id")));
            branch.setBranchName(resultSet.getString("branch_name"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return branch;
    }

    @Override
    protected Branches getEntityByIdHelper(PreparedStatement preparedStatement, int id) {
        try {
            preparedStatement.setInt(1, id);
            return processResultSet(preparedStatement).get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    protected void saveEntityHelper(PreparedStatement preparedStatement, Branches branch) {
        try {
            preparedStatement.setInt(1, branch.getLocation().getId());
            preparedStatement.setString(2, branch.getBranchName());

            int AICheck = preparedStatement.executeUpdate();

            if (AICheck > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    branch.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }


    @Override
    protected void updateEntityHelper(PreparedStatement preparedStatement, Branches branch) {
        try {
            int counter = 1;
            for (Branches.BranchesColumns column : Branches.BranchesColumns.values()) {
                parseEnum(counter, column.getColumnType(), branch.getColumnValue(column), preparedStatement);
                counter++;
            }
            preparedStatement.setInt(counter, branch.getId());
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
    public void saveEntity(Branches branch) {
        String query = "INSERT INTO branches (id, location_id, branch_name) VALUES (?, ?, ?)";
        executeStatement(query, "saveEntity", branch);
    }

    @Override
    public Branches getEntityById(int id) {
        String query = "SELECT * FROM branches WHERE id = ?";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public void updateEntity(Branches branch) {
        StringBuilder query = new StringBuilder("UPDATE branches SET ");
        for (Branches.BranchesColumns column : Branches.BranchesColumns.values()) {
            query.append(column.getColumnName()).append(" = ?, ");
        }
        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        executeStatement(query.toString(), "updateEntity", branch);
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM branches WHERE id = ?";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public List<Branches> getAll() {
        String query = "SELECT * FROM branches";
        return executeStatement(query, "getAll");
    }
}
