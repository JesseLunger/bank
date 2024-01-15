package com.solvd.bank.persistence.jdbcimpl;

import com.solvd.bank.domain.Associate;
import com.solvd.bank.domain.Position;
import com.solvd.bank.domain.Staff;
import com.solvd.bank.utils.jdbcconnectionutils.ConnectionPool;
import com.solvd.bank.utils.jdbcconnectionutils.MySQLFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO extends BaseClassDAO<Staff> implements com.solvd.bank.persistence.IStaffDAO {

    @Override
    public void updatePosition(Staff staff, Position position) {
        String query = "UPDATE locations " +
                "SET position_id = (?) " +
                "WHERE associate_id = (?)";
        try (Connection connection = MySQLFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, position.getId());
            preparedStatement.setInt(2, staff.getAssociate().getId());
            preparedStatement.executeUpdate();
            staff.setPosition(position);
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Staff> getAll() {
        String query = "SELECT * FROM staff;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Staff createEntity(ResultSet resultSet) throws SQLException {
        Staff staff = new Staff();
        Associate associate = new AssociateDAO().getEntityById(resultSet.getInt("associate_id"));
        staff.setAssociate(associate);
        staff.setPosition(new PositionDAO().getEntityById(resultSet.getInt("position_id")));
        staff.setDateHired(resultSet.getTimestamp("date_hired"));
        return staff;
    }

    @Override
    public Staff getEntityById(int id) {
        String query = "SELECT * FROM staff " +
                "WHERE associate_id = (?);";
        ArrayList<Staff> staff = executeStatement(query, "getEntityById", id);
        if (staff == null || staff.isEmpty()) {
            return null;
        }
        return staff.get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(Staff staff) {
        String query = "INSERT INTO staff (associate_id, position_id, date_hired) " +
                "VALUES ((?), (?), (?))";
        executeStatement(query, "saveEntity", staff);
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Staff staff) throws SQLException {
        preparedStatement.setInt(1, staff.getAssociate().getId());
        preparedStatement.setInt(2, staff.getPosition().getId());
        preparedStatement.setTimestamp(3, staff.getDateHired());
    }

    @Override
    public void updateEntity(Staff staff) {
        String query = "UPDATE staff SET position_id = (?), date_hired = (?) WHERE associate_id = (?)";
        executeStatement(query, "updateEntity", staff);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Staff staff) throws SQLException {
        preparedStatement.setInt(1, staff.getPosition().getId());
        preparedStatement.setTimestamp(2, staff.getDateHired());
        preparedStatement.setInt(3, staff.getAssociate().getId());
    }

    @Override
    public void removeEntityById(int id) {
        String query = "DELETE FROM staff WHERE associate_id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
