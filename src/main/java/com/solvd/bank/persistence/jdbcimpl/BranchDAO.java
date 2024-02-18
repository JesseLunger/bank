package com.solvd.bank.persistence.jdbcimpl;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.domain.Staff;
import com.solvd.bank.utils.jdbcconnectionutils.ConnectionPool;
import com.solvd.bank.utils.jdbcconnectionutils.MySQLFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO extends BaseClassDAO<Branch> implements com.solvd.bank.persistence.IBranchDAO {

    @Override
    public ArrayList<Branch> getAllBranchesByLocationId(int id) {
        ArrayList<Branch> branches = new ArrayList<>();
        String query = "SELECT * FROM branches " +
                "WHERE location_id = (?)";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                BranchDAO branchDAO = new BranchDAO();
                while (resultSet.next()) {
                    branches.add(branchDAO.createEntity(resultSet));
                }
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        return branches;
    }

    @Override
    public List<Branch> getAll() {
        String query = "SELECT * FROM branches;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Branch createEntity(ResultSet resultSet) throws SQLException {
        Branch branch = new Branch();
        branch.setId(resultSet.getInt("id"));
        branch.setLocation(new LocationDAO().getEntityById(resultSet.getInt("location_id")));
        branch.setBranchName(resultSet.getString("branch_name"));
        ArrayList<Staff> staff = new BranchHasEmployeeDAO().getAllStaffByBranchId(branch.getId());
        branch.setBranchStaff(staff);
        return branch;
    }

    @Override
    public Branch getEntityById(int id) {
        String query = "SELECT * FROM branches " +
                "WHERE id = (?);";
        ArrayList<Branch> branches = executeStatement(query, "getEntityById", id);
        if (branches == null || branches.isEmpty()) {
            return null;
        }
        return branches.get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(Branch branch) {
        String query = "INSERT INTO branches (location_id, branch_name) " +
                "VALUES ((?), (?))";
        executeStatement(query, "saveEntity", branch);
        Integer autoIncrementValue = getAutoIncrementValue();
        if (autoIncrementValue != null) {
            branch.setId(autoIncrementValue);
        }
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Branch branch) throws SQLException {
        preparedStatement.setInt(1, branch.getLocation().getId());
        preparedStatement.setString(2, branch.getBranchName());
    }

    @Override
    public void updateEntity(Branch branch) {
        String query = "UPDATE branches SET location_id = (?), branch_name = (?) " +
                "WHERE id = (?)";
        executeStatement(query, "updateEntity", branch);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Branch branch) throws SQLException {
        preparedStatement.setInt(1, branch.getLocation().getId());
        preparedStatement.setString(2, branch.getBranchName());
        preparedStatement.setInt(3, branch.getId());
    }

    @Override
    public void removeEntityById(int id) {
        String query = "DELETE FROM branches " +
                "WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
