package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.domain.BranchHasEmployee;
import com.solvd.bank.domain.Staff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BranchHasEmployeeDAO extends BaseClassDAO<BranchHasEmployee> implements com.solvd.bank.persistence.IBranchHasEmployeeDAO {

    @Override
    public ArrayList<Staff> getAllStaffByBranch(Branch branch) {
        ArrayList<BranchHasEmployee> branchHasEmployees = new ArrayList<>(getAll());
        return branchHasEmployees.stream()
                .filter(bhe -> bhe.getBranch().getId() == (branch.getId()))
                .map(BranchHasEmployee::getStaff).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<BranchHasEmployee> getAll() {
        String query = "SELECT * FROM branch_has_employees;";
        return executeStatement(query, "getAll");
    }

    @Override
    protected BranchHasEmployee createEntity(ResultSet resultSet) throws SQLException {
        BranchHasEmployee branchHasEmployee = new BranchHasEmployee();
        branchHasEmployee.setBranch(new BranchDAO().getEntityById(resultSet.getInt("branch_id")));
        branchHasEmployee.setStaff(new StaffDAO().getEntityById(resultSet.getInt("staff_id")));
        return branchHasEmployee;
    }

    @Override
    public BranchHasEmployee getEntityById(int id) {
        String query = "SELECT * FROM branch_has_employees WHERE staff_id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    protected BranchHasEmployee prepareCreateSingleEntityStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return getResultsFromStatement(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(BranchHasEmployee branchHasEmployee) {
        String query = "INSERT INTO branch_has_employees (branch_id, staff_id) VALUES ((?), (?))";
        executeStatement(query, "saveEntity", branchHasEmployee);
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, BranchHasEmployee branchHasEmployee) throws SQLException {
        preparedStatement.setInt(1, branchHasEmployee.getBranch().getId());
        preparedStatement.setInt(2, branchHasEmployee.getStaff().getAssociate().getId());
    }

    @Override
    public void updateEntity(BranchHasEmployee branchHasEmployee) {
        String query = "UPDATE branch_has_employees SET branch_id = (?), staff_id = (?) WHERE staff_id = (?)";
        executeStatement(query, "updateEntity", branchHasEmployee);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, BranchHasEmployee branchHasEmployee) throws SQLException {
        preparedStatement.setInt(1, branchHasEmployee.getBranch().getId());
        preparedStatement.setInt(2, branchHasEmployee.getStaff().getAssociate().getId());
        preparedStatement.setInt(3, branchHasEmployee.getStaff().getAssociate().getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM branch_has_employees WHERE staff_id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }


}
