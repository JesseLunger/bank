package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Positions;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PositionsJdbcImpl extends BaseClassJdbcImpl<Positions> implements IBaseRepository<Positions> {

    @Override
    public List<Positions> getAll() {
        String query = "SELECT * FROM positions;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Positions getAllHelper(ResultSet resultSet) throws SQLException {
        Positions position = new Positions();
        position.setId(resultSet.getInt("id"));
        position.setPosition(resultSet.getString("position"));
        position.setSalary(resultSet.getDouble("salary"));
        position.setHourlyWage(resultSet.getDouble("hourly_wage"));
        return position;
    }

    @Override
    public Positions getEntityById(int id) {
        String query = "SELECT * FROM positions WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Positions getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Positions position) {
        String query = "INSERT INTO positions (position, salary, hourly_wage) VALUES ((?), (?), (?))";
        executeStatement(query, "saveEntity", position);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Positions position) throws SQLException {
        preparedStatement.setString(1, position.getPosition());
        preparedStatement.setDouble(2, position.getSalary());
        preparedStatement.setDouble(3, position.getHourlyWage());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            position.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(Positions position) {
        String query = "UPDATE positions SET position = (?), salary = (?), hourly_wage = (?) WHERE id = (?)";
        executeStatement(query, "updateEntity", position);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Positions position) throws SQLException {
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
