package com.solved.bank.persistence.impl;

import com.solved.bank.domain.Associates;
import com.solved.bank.domain.Positions;
import com.solved.bank.domain.Staff;
import com.solved.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class StaffJdbcImpl extends BaseClassJdbcImpl<Staff> implements IBaseRepository<Staff> {

    @Override
    protected Staff getAllHelper(ResultSet resultSet) {
        Staff staff = new Staff();
        try {
            staff.setAssociate(new AssociatesJdbcImpl().getEntityById(resultSet.getInt("associate_id")));
            staff.setPosition(new PositionsJdbcImpl().getEntityById(resultSet.getInt("position_id")));
            staff.setDateHired(resultSet.getTimestamp("date_hired"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return staff;
    }

    @Override
    protected Staff getEntityByIdHelper(PreparedStatement preparedStatement, int id) {
        try {
            preparedStatement.setInt(1, id);
            return processResultSet(preparedStatement).get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    protected void saveEntityHelper(PreparedStatement preparedStatement, Staff staff) {
        try {
            preparedStatement.setInt(1, staff.getAssociate().getId());
            preparedStatement.setInt(2, staff.getPosition().getId());
            preparedStatement.setTimestamp(3, staff.getDateHired());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    protected void updateEntityHelper(PreparedStatement preparedStatement, Staff staff) {
        try {
            int counter = 1;
            for (Staff.StaffColumns column : Staff.StaffColumns.values()) {
                parseEnum(counter, column.getColumnType(), staff.getColumnValue(column), preparedStatement);
                counter++;
            }
            preparedStatement.setInt(counter, staff.getAssociate().getId());
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
    public void saveEntity(Staff staff) {
        String query = "INSERT INTO staff (associate_id, position_id, date_hired) VALUES (?, ?, ?)";
        executeStatement(query, "saveEntity", staff);
    }

    @Override
    public Staff getEntityById(int id) {
        String query = "SELECT * FROM staff WHERE associate_id = ?";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public void updateEntity(Staff staff) {
        StringBuilder query = new StringBuilder("UPDATE staff SET ");
        for (Staff.StaffColumns column : Staff.StaffColumns.values()) {
            query.append(column.getColumnName()).append(" = ?, ");
        }
        query.setLength(query.length() - 2);
        query.append(" WHERE associate_id = ?");
        executeStatement(query.toString(), "updateEntity", staff);
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM staff WHERE associate_id = ?";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public List<Staff> getAll() {
        String query = "SELECT * FROM staff";
        return executeStatement(query, "getAll");
    }
}
