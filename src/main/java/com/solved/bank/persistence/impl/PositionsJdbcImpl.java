package com.solved.bank.persistence.impl;

import com.solved.bank.domain.Positions;
import com.solved.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PositionsJdbcImpl extends BaseClassJdbcImpl<Positions> implements IBaseRepository<Positions> {

    @Override
    protected Positions getAllHelper(ResultSet resultSet) {
        Positions position = new Positions();
        try {
            position.setId(resultSet.getInt("id"));
            position.setPosition(resultSet.getString("position"));
            position.setSalary(resultSet.getDouble("salary"));
            position.setHourlyWage(resultSet.getDouble("hourly_wage"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return position;
    }

    @Override
    protected Positions getEntityByIdHelper(PreparedStatement preparedStatement, int id) {
        try {
            preparedStatement.setInt(1, id);
            return processResultSet(preparedStatement).get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    protected void saveEntityHelper(PreparedStatement preparedStatement, Positions position) {
        try {
            preparedStatement.setString(1, position.getPosition());
            preparedStatement.setDouble(2, position.getSalary());
            preparedStatement.setDouble(3, position.getHourlyWage());
            int AICheck = preparedStatement.executeUpdate();
            if (AICheck > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    position.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    protected void updateEntityHelper(PreparedStatement preparedStatement, Positions position) {
        try {
            int counter = 1;
            for (Positions.PositionsColumns column : Positions.PositionsColumns.values()) {
                parseEnum(counter, column.getColumnType(), position.getColumnValue(column), preparedStatement);
                counter++;
            }
            preparedStatement.setInt(counter, position.getId());
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
    public void saveEntity(Positions position) {
        String query = "INSERT INTO positions (position, salary, hourly_wage) VALUES (?, ?, ?)";
        executeStatement(query, "saveEntity", position);
    }

    @Override
    public Positions getEntityById(int id) {
        String query = "SELECT * FROM positions WHERE id = ?";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public void updateEntity(Positions position) {
        StringBuilder query = new StringBuilder("UPDATE positions SET ");
        for (Positions.PositionsColumns column : Positions.PositionsColumns.values()) {
            query.append(column.getColumnName()).append(" = ?, ");
        }
        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        executeStatement(query.toString(), "updateEntity", position);
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM positions WHERE id = ?";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public List<Positions> getAll() {
        String query = "SELECT * FROM positions";
        return executeStatement(query, "getAll");
    }
}
