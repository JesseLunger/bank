package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.BranchHasEmployees;
import com.solvd.bank.domain.Branches;
import com.solvd.bank.domain.Staff;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BranchHasEmployeesJdbcImpl extends BaseClassJdbcImpl<BranchHasEmployees> implements IBaseRepository<BranchHasEmployees> {

    @Override
    protected BranchHasEmployees getAllHelper(ResultSet resultSet) {
        BranchHasEmployees branchHasEmployees = new BranchHasEmployees();
        try {
            Branches branch = new BranchesJdbcImpl().getEntityById(resultSet.getInt("branch_id"));
            Staff staff = new StaffJdbcImpl().getEntityById(resultSet.getInt("staff_id"));

            branchHasEmployees.setBranch(branch);
            branchHasEmployees.setStaff(staff);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return branchHasEmployees;
    }

    @Override
    protected BranchHasEmployees getEntityByIdHelper(PreparedStatement preparedStatement, int id) {
        try {
            preparedStatement.setInt(1, id);
            return processResultSet(preparedStatement).get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    protected void saveEntityHelper(PreparedStatement preparedStatement, BranchHasEmployees branchHasEmployees) {
        try {
            preparedStatement.setInt(1, branchHasEmployees.getBranch().getId());
            preparedStatement.setInt(2, branchHasEmployees.getStaff().getAssociate().getId());
            preparedStatement.setInt(3, branchHasEmployees.getStaff().getPosition().getId());
            preparedStatement.setTimestamp(4, branchHasEmployees.getStaff().getDateHired());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    protected void updateEntityHelper(PreparedStatement preparedStatement, BranchHasEmployees branchHasEmployees) {
        try {
            preparedStatement.setInt(1, branchHasEmployees.getBranch().getId());
            preparedStatement.setInt(2, branchHasEmployees.getStaff().getAssociate().getId());
            preparedStatement.setInt(3, branchHasEmployees.getStaff().getPosition().getId());
            preparedStatement.setTimestamp(4, branchHasEmployees.getStaff().getDateHired());
            preparedStatement.setInt(5, branchHasEmployees.getBranch().getId());
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
    public void saveEntity(BranchHasEmployees branchHasEmployees) {
        String query = "INSERT INTO branch_has_employees (branch_id, staff_id) VALUES (?, ?)";
        executeStatement(query, "saveEntity", branchHasEmployees);
    }

    @Override
    public BranchHasEmployees getEntityById(int id) {
        String query = "SELECT * FROM branch_has_employees WHERE branch_id = ?";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public void updateEntity(BranchHasEmployees branchHasEmployees) {
        String query = "UPDATE branch_has_employees SET branch_id = ?, staff_id = ? WHERE branch_id = ?";
        executeStatement(query, "updateEntity", branchHasEmployees);
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM branch_has_employees WHERE branch_id = ?";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public List<BranchHasEmployees> getAll() {
        String query = "SELECT * FROM branch_has_employees";
        return executeStatement(query, "getAll");
    }
}
