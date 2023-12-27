package com.solvd.bank.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Location;
import com.solvd.bank.utils.ConnectionPool;

public class CityDAO extends BaseClassDAO<City> implements com.solvd.bank.persistence.ICityDAO {

    @Override
    public ArrayList<Location> getLocationsByCity(City city) {
        ArrayList<Location> locations = new ArrayList<>();
        String query = "SELECT * FROM locations WHERE city_id = (?);";
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1, city.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                LocationDAO locationDAO = new LocationDAO();
                while (resultSet.next()){
                    locations.add(locationDAO.createEntity(resultSet));
                }
            }
        }catch (SQLException | InterruptedException e){
            LOGGER.error(e.getMessage());
        }
        return locations;
    }

    @Override
    public List<City> getAll() {
        String query = "SELECT * FROM cities;";
        return executeStatement(query, "getAll");
    }
    @Override
    public City createEntity(ResultSet resultSet) throws SQLException{
        City city = new City();
        city.setId(resultSet.getInt("id"));
        city.setName(resultSet.getString("name"));
        city.setCountry(new CountryDAO().getEntityById( resultSet.getInt("country_id")));
        return city;
    }

    @Override
    public City getEntityById(int id) {
        String query = "SELECT * FROM cities WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(City city) {
        String query = "INSERT INTO cities (name, country_id) VALUES ((?), (?))";
        executeStatement(query, "saveEntity", city);
        Integer autoIncrementValue = getAutoIncrementValue();
        if (autoIncrementValue != null){
            city.setId(autoIncrementValue);
        }
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, City city) throws SQLException {
        preparedStatement.setString(1, city.getName());
        preparedStatement.setInt(2, city.getCountry().getId());
    }

    @Override
    public void updateEntity(City city) {
        String query = "UPDATE cities set name = (?), country_id = (?) where id = (?);";
        executeStatement(query, "updateEntity", city);
    }
    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, City city) throws SQLException{
        preparedStatement.setString(1, city.getName());
        preparedStatement.setInt(2, city.getCountry().getId());
        preparedStatement.setInt(3, city.getId());
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM cities WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);

    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
    }
}
