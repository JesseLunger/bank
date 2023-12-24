package com.solvd.bank.persistence.impl;

import com.solvd.bank.persistence.CitiesRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.solvd.bank.domain.City;

public class CitiesJdbcImpl extends BaseClassJdbcImpl<City> implements CitiesRepository {


    @Override
    public List<City> getAll() {
        String query = "SELECT * FROM cities;";
        return executeStatement(query, "getAll");
    }
    @Override
    public City getAllHelper(ResultSet resultSet) throws SQLException{
        City city = new City();
        city.setId(resultSet.getInt("id"));
        city.setName(resultSet.getString("name"));
        city.setCountry(new CountriesJdbcImpl().getEntityById( resultSet.getInt("country_id")));
        return city;
    }

    @Override
    public City getEntityById(int id) {
        String query = "SELECT * FROM cities WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public City getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(City city) {
        String query = "INSERT INTO cities (name, country_id) VALUES ((?), (?))";
        executeStatement(query, "saveEntity", city);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, City city) throws SQLException {
        preparedStatement.setString(1, city.getName());
        preparedStatement.setInt(2, city.getCountry().getId());
        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null){
            city.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(City city) {
        String query = "UPDATE cities set name = (?), country_id = (?) where id = (?);";
        executeStatement(query, "updateEntity", city);
    }
    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, City city) throws SQLException{
        preparedStatement.setString(1, city.getName());
        preparedStatement.setInt(2, city.getCountry().getId());
        preparedStatement.setInt(3, city.getId());
        preparedStatement.execute();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM cities WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);

    }

    @Override
    public void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }










}
