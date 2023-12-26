package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Country;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ILocationDAO;
import com.solvd.bank.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocationDAO extends BaseClassDAO<Location> implements ILocationDAO {

    @Override
    public void updateCity(Location location, City city) {
        String query = "UPDATE locations SET cities_id = (?) WHERE id = (?);";
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1, city.getId());
            preparedStatement.setInt(2, location.getId());
            preparedStatement.executeUpdate();
            location.setCity(city);
        }catch (InterruptedException | SQLException e){
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Location> getAll() {
        String query = "SELECT * FROM locations";
        return executeStatement(query, "getAll");
    }
    @Override
    protected Location createEntity(ResultSet resultSet) throws SQLException{
        Location location = new Location();
        location.setId(resultSet.getInt("id"));
        location.setCity(new CityDAO().getEntityById(resultSet.getInt("city_id")));
        location.setZipCode(resultSet.getString("zip_code"));
        location.setAddress(resultSet.getString("address"));
        return location;
    }

    @Override
    public Location getEntityById(int id) {
        String query = "SELECT * FROM locations WHERE id = ?";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    protected Location prepareCreateSingleEntityStatement(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
        return getResultsFromStatement(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Location location) {
        String query = "INSERT INTO locations (city_id, zip_code, address) VALUES (?, ?, ?);";
        executeStatement(query, "saveEntity", location);
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Location location) throws SQLException{
            preparedStatement.setInt(1, location.getCity().getId());
            preparedStatement.setString(2, location.getZipCode());
            preparedStatement.setString(3, location.getAddress());
            Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
            if (autoIncrementValue != null){
                location.setId(autoIncrementValue);
            }

    }

    @Override
    public void updateEntity(Location location) {
        String query = "UPDATE locations SET city_id = (?), zip_code = (?), address = (?) WHERE id = (?);";
        executeStatement(query, "updateEntity", location);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Location location) throws SQLException{
        preparedStatement.setInt(1, location.getCity().getId());
        preparedStatement.setString(2, location.getZipCode());
        preparedStatement.setString(1, location.getAddress());
        preparedStatement.setInt(4, location.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM locations WHERE id = ?";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }
}
