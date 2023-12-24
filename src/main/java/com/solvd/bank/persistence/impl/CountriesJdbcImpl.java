package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Country;
import com.solvd.bank.persistence.CountriesRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CountriesJdbcImpl extends BaseClassJdbcImpl<Country> implements CountriesRepository {

    @Override
    public List<Country> getAll() {
        String query = "SELECT * FROM countries;";
        return executeStatement(query, "getAll");
    }

    @Override
    protected Country getAllHelper(ResultSet resultSet) throws SQLException {
        Country country = new Country();
        country.setId(resultSet.getInt("id"));
        country.setName(resultSet.getString("name"));
        return country;
    }

    @Override
    public Country getEntityById(int id) {
        String query = "SELECT * FROM countries WHERE id = ?";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    protected Country getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Country country) {
        String query = "INSERT INTO countries (name) VALUES (?)";
        executeStatement(query, "saveEntity", country);
    }

    @Override
    protected void saveEntityHelper(PreparedStatement preparedStatement, Country country) throws SQLException {
        preparedStatement.setString(1, country.getName());
        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            country.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(Country country) {
        String query = "UPDATE countries SET name = ? WHERE id = ?";
        executeStatement(query, "updateEntity", country);
    }

    @Override
    protected void updateEntityHelper(PreparedStatement preparedStatement, Country country) throws SQLException {
        preparedStatement.setString(1, country.getName());
        preparedStatement.setInt(2, country.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM countries WHERE id = ?";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
