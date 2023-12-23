package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.BranchHasEmployees;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BranchHasEmployeesJdbcImpl extends BaseClassJdbcImpl<BranchHasEmployees> implements IBaseRepository<BranchHasEmployees> {

    @Override
    public List<BranchHasEmployees> getAll() {
        String query = "SELECT * FROM branch_has_employees;";
        return executeStatement(query, "getAll");
    }

    @Override
    public BranchHasEmployees getAllHelper(ResultSet resultSet) throws SQLException {
        BranchHasEmployees branchHasEmployees = new BranchHasEmployees();
        branchHasEmployees.setBranch(new BranchesJdbcImpl().getEntityById(resultSet.getInt("branch_id")));
        branchHasEmployees.setStaff(new StaffJdbcImpl().getEntityById(resultSet.getInt("staff_associate_id")));
        return branchHasEmployees;
    }

    @Override
    public BranchHasEmployees getEntityById(int id) {
        String query = "SELECT * FROM branch_has_employees WHERE staff_associate_id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public BranchHasEmployees getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(BranchHasEmployees branchHasEmployees) {
        String query = "INSERT INTO branch_has_employees (branch_id, staff_associate_id) VALUES ((?), (?))";
        executeStatement(query, "saveEntity", branchHasEmployees);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, BranchHasEmployees branchHasEmployees) throws SQLException {
        preparedStatement.setInt(1, branchHasEmployees.getBranch().getId());
        preparedStatement.setInt(2, branchHasEmployees.getStaff().getAssociate().getId());
    }

    @Override
    public void updateEntity(BranchHasEmployees branchHasEmployees) {
        String query = "UPDATE branch_has_employees SET branch_id = (?), staff_associate_id = (?) WHERE staff_associate_id = (?)";
        executeStatement(query, "updateEntity", branchHasEmployees);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, BranchHasEmployees branchHasEmployees) throws SQLException {
        preparedStatement.setInt(1, branchHasEmployees.getBranch().getId());
        preparedStatement.setInt(2, branchHasEmployees.getStaff().getAssociate().getId());
        preparedStatement.setInt(3, branchHasEmployees.getStaff().getAssociate().getId());
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
