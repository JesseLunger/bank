package com.solvd.bank.persistence.jdbcimpl;

import com.solvd.bank.domain.BranchHasEmployee;
import com.solvd.bank.domain.Staff;
import com.solvd.bank.utils.jdbcconnectionutils.ConnectionPool;
import com.solvd.bank.utils.jdbcconnectionutils.MySQLFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchHasEmployeeDAO extends BaseClassDAO<BranchHasEmployee> implements com.solvd.bank.persistence.IBranchHasEmployeeDAO {

    @Override
    public ArrayList<Staff> getAllStaffByBranchId(int id) {
        ArrayList<Staff> staff = new ArrayList<>();
        StaffDAO staffDAO = new StaffDAO();
        String query = "SELECT st.* FROM branch_has_employees bhe " +
                "LEFT JOIN staff st ON bhe.staff_id = st.associate_id " +
                "WHERE branch_id = (?);";
        try (Connection connection = MySQLFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    staff.add(staffDAO.createEntity(resultSet));
                }
            }
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return staff;
    }

    @Override
    public List<BranchHasEmployee> getAll() {
        String query = "SELECT * FROM branch_has_employees;";
        return executeStatement(query, "getAll");
    }

    @Override
    public BranchHasEmployee createEntity(ResultSet resultSet) throws SQLException {
        BranchHasEmployee branchHasEmployee = new BranchHasEmployee();
        branchHasEmployee.setBranch(new BranchDAO().getEntityById(resultSet.getInt("branch_id")));
        branchHasEmployee.setStaff(new StaffDAO().getEntityById(resultSet.getInt("staff_id")));
        return branchHasEmployee;
    }

    @Override
    public BranchHasEmployee getEntityById(int id) {
        String query = "SELECT * FROM branch_has_employees " +
                "WHERE staff_id = (?);";
        ArrayList<BranchHasEmployee> branchHasEmployees = executeStatement(query, "getEntityById", id);
        if (branchHasEmployees == null || branchHasEmployees.isEmpty()) {
            return null;
        }
        return branchHasEmployees.get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(BranchHasEmployee branchHasEmployee) {
        String query = "INSERT INTO branch_has_employees (branch_id, staff_id) " +
                "VALUES ((?), (?))";
        executeStatement(query, "saveEntity", branchHasEmployee);
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, BranchHasEmployee branchHasEmployee) throws SQLException {
        preparedStatement.setInt(1, branchHasEmployee.getBranch().getId());
        preparedStatement.setInt(2, branchHasEmployee.getStaff().getAssociate().getId());
    }

    @Override
    public void updateEntity(BranchHasEmployee branchHasEmployee) {
        new BranchDAO().updateEntity(branchHasEmployee.getBranch());
        new StaffDAO().updateEntity(branchHasEmployee.getStaff());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, BranchHasEmployee branchHasEmployee) throws SQLException {
        //placeHolder function
    }

    @Override
    public void removeEntityById(int id) {
        String query = "DELETE FROM branch_has_employees " +
                "WHERE staff_id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
