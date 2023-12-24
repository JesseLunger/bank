package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Staff;
import com.solvd.bank.persistence.StaffRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StaffJdbcImpl extends BaseClassJdbcImpl<Staff> implements StaffRepository {

    @Override
    public List<Staff> getAll() {
        String query = "SELECT * FROM staff;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Staff getAllHelper(ResultSet resultSet) throws SQLException {
        Staff staff = new Staff();
        staff.setAssociate(new AssociatesJdbcImpl().getEntityById(resultSet.getInt("associate_id")));
        staff.setPosition(new PositionsJdbcImpl().getEntityById(resultSet.getInt("position_id")));
        staff.setDateHired(resultSet.getTimestamp("date_hired"));
        return staff;
    }

    @Override
    public Staff getEntityById(int id) {
        String query = "SELECT * FROM staff WHERE associate_id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Staff getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Staff staff) {
        String query = "INSERT INTO staff (associate_id, position_id, date_hired) VALUES ((?), (?), (?))";
        executeStatement(query, "saveEntity", staff);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Staff staff) throws SQLException {
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
    public void updateEntityHelper(PreparedStatement preparedStatement, Staff staff) throws SQLException {
        preparedStatement.setInt(1, staff.getPosition().getId());
        preparedStatement.setTimestamp(2, staff.getDateHired());
        preparedStatement.setInt(3, staff.getAssociate().getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM staff WHERE associate_id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
