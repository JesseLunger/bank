package com.solvd.bank.persistence.jdbcimpl;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.domain.Position;
import com.solvd.bank.persistence.IPositionDAO;
import com.solvd.bank.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO extends BaseClassDAO<Position> implements IPositionDAO {

    @Override
    public ArrayList<Branch> getBranchesWithMissingPosition(String positionName) {
        ArrayList<Branch> branches = new ArrayList<>();
        String query =  "SELECT * FROM branches br " +
                        "WHERE NOT EXISTS ( " +
                        "SELECT 1 FROM positions pos " +
                        "LEFT JOIN staff st ON pos.id = st.position_id " +
                        "LEFT JOIN branch_has_employees bhe ON st.associate_id = bhe.staff_id " +
                        "WHERE bhe.branch_id = br.id AND pos.position = (?));";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, positionName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                BranchDAO branchDAO = new BranchDAO();
                while (resultSet.next()) {
                    branches.add(branchDAO.createEntity(resultSet));
                }
            }
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return branches;
    }

    @Override
    public List<Position> getAll() {
        String query = "SELECT * FROM positions;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Position createEntity(ResultSet resultSet) throws SQLException {
        Position position = new Position();
        position.setId(resultSet.getInt("id"));
        position.setPosition(resultSet.getString("position"));
        position.setSalary(resultSet.getDouble("salary"));
        position.setHourlyWage(resultSet.getDouble("hourly_wage"));
        return position;
    }

    @Override
    public Position getEntityById(int id) {
        String query =  "SELECT * FROM positions " +
                        "WHERE id = (?);";
        ArrayList<Position> positions = executeStatement(query, "getEntityById", id);
        if (positions == null || positions.isEmpty()) {
            return null;
        }
        return positions.get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(Position position) {
        String query =  "INSERT INTO positions (position, salary, hourly_wage) " +
                        "VALUES ((?), (?), (?))";
        executeStatement(query, "saveEntity", position);
        Integer autoIncrementValue = getAutoIncrementValue();
        if (autoIncrementValue != null) {
            position.setId(autoIncrementValue);
        }
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Position position) throws SQLException {
        preparedStatement.setString(1, position.getPosition());
        preparedStatement.setDouble(2, position.getSalary());
        preparedStatement.setDouble(3, position.getHourlyWage());
    }

    @Override
    public void updateEntity(Position position) {
        String query =  "UPDATE positions SET position = (?), salary = (?), hourly_wage = (?) " +
                        "WHERE id = (?)";
        executeStatement(query, "updateEntity", position);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Position position) throws SQLException {
        preparedStatement.setString(1, position.getPosition());
        preparedStatement.setDouble(2, position.getSalary());
        preparedStatement.setDouble(3, position.getHourlyWage());
        preparedStatement.setInt(4, position.getId());
    }

    @Override
    public void removeEntityById(int id) {
        String query =  "DELETE FROM positions " +
                        "WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
