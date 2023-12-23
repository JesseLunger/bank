package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Associates;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AssociatesJdbcImpl extends BaseClassJdbcImpl<Associates> implements IBaseRepository<Associates> {

    @Override
    public List<Associates> getAll() {
        String query = "SELECT * FROM associates;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Associates getAllHelper(ResultSet resultSet) throws SQLException {
        Associates associates = new Associates();
        associates.setId(resultSet.getInt("id"));
        associates.setLocation(new LocationsJdbcImpl().getEntityById(resultSet.getInt("location_id")));
        associates.setPrimaryName(resultSet.getString("primary_name"));
        associates.setSecondaryName(resultSet.getString("secondary_name"));
        associates.setDateJoined(resultSet.getTimestamp("date_joined"));
        associates.setEmail(resultSet.getString("email"));
        associates.setPhoneNumber(resultSet.getString("phone_number"));
        return associates;
    }

    @Override
    public Associates getEntityById(int id) {
        String query = "SELECT * FROM associates WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Associates getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Associates associates) {
        String query = "INSERT INTO associates (location_id, primary_name, secondary_name, date_joined, email, phone_number) VALUES ((?), (?), (?), (?), (?), (?))";
        executeStatement(query, "saveEntity", associates);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Associates associates) throws SQLException {
        preparedStatement.setInt(1, associates.getLocation().getId());
        preparedStatement.setString(2, associates.getPrimaryName());
        preparedStatement.setString(3, associates.getSecondaryName());
        preparedStatement.setTimestamp(4, associates.getDateJoined());
        preparedStatement.setString(5, associates.getEmail());
        preparedStatement.setString(6, associates.getPhoneNumber());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            associates.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(Associates associates) {
        String query = "UPDATE associates SET location_id = (?), primary_name = (?), secondary_name = (?), date_joined = (?), email = (?), phone_number = (?) WHERE id = (?)";
        executeStatement(query, "updateEntity", associates);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Associates associates) throws SQLException {
        preparedStatement.setInt(1, associates.getLocation().getId());
        preparedStatement.setString(2, associates.getPrimaryName());
        preparedStatement.setString(3, associates.getSecondaryName());
        preparedStatement.setTimestamp(4, associates.getDateJoined());
        preparedStatement.setString(5, associates.getEmail());
        preparedStatement.setString(6, associates.getPhoneNumber());
        preparedStatement.setInt(7, associates.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM associates WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
