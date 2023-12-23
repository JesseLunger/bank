package com.solvd.bank.persistence.impl;

import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.solvd.bank.domain.Cities;

public class CitiesJdbcImpl extends BaseClassJdbcImpl<Cities> implements IBaseRepository<Cities> {


    @Override
    public List<Cities> getAll() {
        String query = "SELECT * FROM cities;";
        return executeStatement(query, "getAll");
    }
    @Override
    public Cities getAllHelper(ResultSet resultSet) throws SQLException{
        Cities cities = new Cities();
        cities.setId(resultSet.getInt("id"));
        cities.setName(resultSet.getString("name"));
        cities.setCountry(new CountriesJdbcImpl().getEntityById( resultSet.getInt("country_id")));
        return cities;
    }

    @Override
    public Cities getEntityById(int id) {
        String query = "SELECT * FROM cities WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Cities getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Cities cities) {
        String query = "INSERT INTO cities (name, country_id) VALUES ((?), (?))";
        executeStatement(query, "saveEntity", cities);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Cities city) throws SQLException {
        preparedStatement.setString(1, city.getName());
        preparedStatement.setInt(2, city.getCountry().getId());
        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null){
            city.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(Cities city) {
        String query = "UPDATE cities set name = (?), country_id = (?) where id = (?);";
        executeStatement(query, "updateEntity", city);
    }
    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Cities city) throws SQLException{
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
