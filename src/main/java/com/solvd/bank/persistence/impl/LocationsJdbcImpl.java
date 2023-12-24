package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.LocationsRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocationsJdbcImpl extends BaseClassJdbcImpl<Location> implements LocationsRepository {


    @Override
    public List<Location> getAll() {
        String query = "SELECT * FROM locations";
        return executeStatement(query, "getAll");
    }
    @Override
    protected Location getAllHelper(ResultSet resultSet) throws SQLException{
        Location location = new Location();
        location.setId(resultSet.getInt("id"));
        location.setCity(new CitiesJdbcImpl().getEntityById(resultSet.getInt("city_id")));
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
    protected Location getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Location location) {
        String query = "INSERT INTO locations (city_id, zip_code, address) VALUES (?, ?, ?);";
        executeStatement(query, "saveEntity", location);
    }

    @Override
    protected void saveEntityHelper(PreparedStatement preparedStatement, Location location) throws SQLException{
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
    protected void updateEntityHelper(PreparedStatement preparedStatement, Location location) throws SQLException{
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
    protected void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }










}