package com.solvd.bank.persistence.jdbcimpl;

import com.solvd.bank.domain.Associate;
import com.solvd.bank.domain.Location;
import com.solvd.bank.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssociateDAO extends BaseClassDAO<Associate> implements com.solvd.bank.persistence.IAssociateDAO {

    @Override
    public ArrayList<Associate> getAllAssociatesByLocationId(int id) {
        ArrayList<Associate> associates = new ArrayList<>();
        String query =  "SELECT * FROM associates " +
                        "Where location_id = (?);";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                AssociateDAO associateDAO = new AssociateDAO();
                while (resultSet.next()) {
                    associates.add(associateDAO.createEntity(resultSet));
                }
            }
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return associates;
    }

    @Override
    public List<Associate> getAll() {
        String query = "SELECT * FROM associates;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Associate createEntity(ResultSet resultSet) throws SQLException {
        Associate associate = new Associate();
        associate.setId(resultSet.getInt("id"));
        Location location = new LocationDAO().getEntityById(resultSet.getInt("location_id"));
        associate.setLocation(location);
        associate.setPrimaryName(resultSet.getString("primary_name"));
        associate.setSecondaryName(resultSet.getString("secondary_name"));
        associate.setDateJoined(resultSet.getTimestamp("date_joined"));
        associate.setEmail(resultSet.getString("email"));
        associate.setPhoneNumber(resultSet.getString("phone_number"));
        return associate;
    }

    @Override
    public Associate getEntityById(int id) {
        String query =  "SELECT * FROM associates " +
                        "WHERE id = (?);";
        ArrayList<Associate> associates = executeStatement(query, "getEntityById", id);
        if (associates == null || associates.isEmpty()) {
            return null;
        }
        return associates.get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(Associate associate) {
        String query =  "INSERT INTO associates (location_id, primary_name, secondary_name, date_joined, email, phone_number) " +
                        "VALUES ((?), (?), (?), (?), (?), (?))";
        executeStatement(query, "saveEntity", associate);
        Integer autoIncrementValue = getAutoIncrementValue();
        if (autoIncrementValue != null) {
            associate.setId(autoIncrementValue);
        }
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Associate associate) throws SQLException {
        preparedStatement.setInt(1, associate.getLocation().getId());
        preparedStatement.setString(2, associate.getPrimaryName());
        preparedStatement.setString(3, associate.getSecondaryName());
        preparedStatement.setTimestamp(4, associate.getDateJoined());
        preparedStatement.setString(5, associate.getEmail());
        preparedStatement.setString(6, associate.getPhoneNumber());
    }

    @Override
    public void updateEntity(Associate associate) {
        String query =  "UPDATE associates SET location_id = (?), primary_name = (?), secondary_name = (?), date_joined = (?), email = (?), phone_number = (?) " +
                        "WHERE id = (?)";
        executeStatement(query, "updateEntity", associate);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Associate associate) throws SQLException {
        preparedStatement.setInt(1, associate.getLocation().getId());
        preparedStatement.setString(2, associate.getPrimaryName());
        preparedStatement.setString(3, associate.getSecondaryName());
        preparedStatement.setTimestamp(4, associate.getDateJoined());
        preparedStatement.setString(5, associate.getEmail());
        preparedStatement.setString(6, associate.getPhoneNumber());
        preparedStatement.setInt(7, associate.getId());
    }

    @Override
    public void removeEntityById(int id) {
        String query =  "DELETE FROM associates " +
                        "WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
