package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Country;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ICountryDAO;
import com.solvd.bank.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO extends BaseClassDAO<Country> implements ICountryDAO {

    @Override
    public ArrayList<Location> getAllLocationsByCountry(Country country) {
        ArrayList<Location> locations = new ArrayList<>();
        String query = "SELECT * FROM cities WHERE country_id = (?);";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, country.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                CityDAO cityDAO = new CityDAO();
                while (resultSet.next()) {
                    City city = cityDAO.createEntity(resultSet);
                    locations.addAll(cityDAO.getLocationsByCity(city));
                }
            }
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return locations;
    }

    @Override
    public List<Country> getAll() {
        String query = "SELECT * FROM countries;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Country createEntity(ResultSet resultSet) throws SQLException {
        Country country = new Country();
        country.setId(resultSet.getInt("id"));
        country.setName(resultSet.getString("name"));
        return country;
    }

    @Override
    public Country getEntityById(int id) {
        String query = "SELECT * FROM countries WHERE id = ?";
        ArrayList<Country> countries = executeStatement(query, "getEntityById", id);
        if (countries == null || countries.isEmpty()) {
            return null;
        }
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(Country country) {
        String query = "INSERT INTO countries (name) VALUES (?)";
        executeStatement(query, "saveEntity", country);
        Integer autoIncrementValue = getAutoIncrementValue();
        if (autoIncrementValue != null) {
            country.setId(autoIncrementValue);
        }
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Country country) throws SQLException {
        preparedStatement.setString(1, country.getName());
    }

    @Override
    public void updateEntity(Country country) {
        String query = "UPDATE countries SET name = ? WHERE id = ?";
        executeStatement(query, "updateEntity", country);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Country country) throws SQLException {
        preparedStatement.setString(1, country.getName());
        preparedStatement.setInt(2, country.getId());
    }

    @Override
    public void removeEntityById(int id) {
        String query = "DELETE FROM countries WHERE id = ?";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
