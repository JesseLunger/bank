package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.BranchHasEmployee;
import com.solvd.bank.persistence.BranchHasEmployeesRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BranchHasEmployeesJdbcImpl extends BaseClassJdbcImpl<BranchHasEmployee> implements BranchHasEmployeesRepository {

    @Override
    public List<BranchHasEmployee> getAll() {
        String query = "SELECT * FROM branch_has_employees;";
        return executeStatement(query, "getAll");
    }

    @Override
    public BranchHasEmployee getAllHelper(ResultSet resultSet) throws SQLException {
        BranchHasEmployee branchHasEmployee = new BranchHasEmployee();
        branchHasEmployee.setBranch(new BranchesJdbcImpl().getEntityById(resultSet.getInt("branch_id")));
        branchHasEmployee.setStaff(new StaffJdbcImpl().getEntityById(resultSet.getInt("staff_associate_id")));
        return branchHasEmployee;
    }

    @Override
    public BranchHasEmployee getEntityById(int id) {
        String query = "SELECT * FROM branch_has_employees WHERE staff_associate_id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public BranchHasEmployee getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(BranchHasEmployee branchHasEmployee) {
        String query = "INSERT INTO branch_has_employees (branch_id, staff_associate_id) VALUES ((?), (?))";
        executeStatement(query, "saveEntity", branchHasEmployee);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, BranchHasEmployee branchHasEmployee) throws SQLException {
        preparedStatement.setInt(1, branchHasEmployee.getBranch().getId());
        preparedStatement.setInt(2, branchHasEmployee.getStaff().getAssociate().getId());
    }

    @Override
    public void updateEntity(BranchHasEmployee branchHasEmployee) {
        String query = "UPDATE branch_has_employees SET branch_id = (?), staff_associate_id = (?) WHERE staff_associate_id = (?)";
        executeStatement(query, "updateEntity", branchHasEmployee);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, BranchHasEmployee branchHasEmployee) throws SQLException {
        preparedStatement.setInt(1, branchHasEmployee.getBranch().getId());
        preparedStatement.setInt(2, branchHasEmployee.getStaff().getAssociate().getId());
        preparedStatement.setInt(3, branchHasEmployee.getStaff().getAssociate().getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM branch_has_employees WHERE staff_associate_id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
