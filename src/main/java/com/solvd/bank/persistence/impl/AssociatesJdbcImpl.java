package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Associate;
import com.solvd.bank.persistence.AssociatesRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AssociatesJdbcImpl extends BaseClassJdbcImpl<Associate> implements AssociatesRepository {

    @Override
    public List<Associate> getAll() {
        String query = "SELECT * FROM associates;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Associate getAllHelper(ResultSet resultSet) throws SQLException {
        Associate associate = new Associate();
        associate.setId(resultSet.getInt("id"));
        associate.setLocation(new LocationsJdbcImpl().getEntityById(resultSet.getInt("location_id")));
        associate.setPrimaryName(resultSet.getString("primary_name"));
        associate.setSecondaryName(resultSet.getString("secondary_name"));
        associate.setDateJoined(resultSet.getTimestamp("date_joined"));
        associate.setEmail(resultSet.getString("email"));
        associate.setPhoneNumber(resultSet.getString("phone_number"));
        return associate;
    }

    @Override
    public Associate getEntityById(int id) {
        String query = "SELECT * FROM associates WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Associate getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Associate associate) {
        String query = "INSERT INTO associates (location_id, primary_name, secondary_name, date_joined, email, phone_number) VALUES ((?), (?), (?), (?), (?), (?))";
        executeStatement(query, "saveEntity", associate);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Associate associate) throws SQLException {
        preparedStatement.setInt(1, associate.getLocation().getId());
        preparedStatement.setString(2, associate.getPrimaryName());
        preparedStatement.setString(3, associate.getSecondaryName());
        preparedStatement.setTimestamp(4, associate.getDateJoined());
        preparedStatement.setString(5, associate.getEmail());
        preparedStatement.setString(6, associate.getPhoneNumber());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            associate.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(Associate associate) {
        String query = "UPDATE associates SET location_id = (?), primary_name = (?), secondary_name = (?), date_joined = (?), email = (?), phone_number = (?) WHERE id = (?)";
        executeStatement(query, "updateEntity", associate);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Associate associate) throws SQLException {
        preparedStatement.setInt(1, associate.getLocation().getId());
        preparedStatement.setString(2, associate.getPrimaryName());
        preparedStatement.setString(3, associate.getSecondaryName());
        preparedStatement.setTimestamp(4, associate.getDateJoined());
        preparedStatement.setString(5, associate.getEmail());
        preparedStatement.setString(6, associate.getPhoneNumber());
        preparedStatement.setInt(7, associate.getId());
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
