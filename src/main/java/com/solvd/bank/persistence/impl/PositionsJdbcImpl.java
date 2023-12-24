package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Position;
import com.solvd.bank.persistence.PositionsRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PositionsJdbcImpl extends BaseClassJdbcImpl<Position> implements PositionsRepository {

    @Override
    public List<Position> getAll() {
        String query = "SELECT * FROM positions;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Position getAllHelper(ResultSet resultSet) throws SQLException {
        Position position = new Position();
        position.setId(resultSet.getInt("id"));
        position.setPosition(resultSet.getString("position"));
        position.setSalary(resultSet.getDouble("salary"));
        position.setHourlyWage(resultSet.getDouble("hourly_wage"));
        return position;
    }

    @Override
    public Position getEntityById(int id) {
        String query = "SELECT * FROM positions WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Position getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Position position) {
        String query = "INSERT INTO positions (position, salary, hourly_wage) VALUES ((?), (?), (?))";
        executeStatement(query, "saveEntity", position);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Position position) throws SQLException {
        preparedStatement.setString(1, position.getPosition());
        preparedStatement.setDouble(2, position.getSalary());
        preparedStatement.setDouble(3, position.getHourlyWage());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            position.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(Position position) {
        String query = "UPDATE positions SET position = (?), salary = (?), hourly_wage = (?) WHERE id = (?)";
        executeStatement(query, "updateEntity", position);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Position position) throws SQLException {
        preparedStatement.setString(1, position.getPosition());
        preparedStatement.setDouble(2, position.getSalary());
        preparedStatement.setDouble(3, position.getHourlyWage());
        preparedStatement.setInt(4, position.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM positions WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
