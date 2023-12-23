package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Countries;
import com.solvd.bank.persistence.CountriesRepository;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CountriesJdbcImpl extends BaseClassJdbcImpl<Countries> implements IBaseRepository<Countries>, CountriesRepository {

    @Override
    public List<Countries> getAll() {
        String query = "SELECT * FROM countries;";
        return executeStatement(query, "getAll");
    }

    @Override
    protected Countries getAllHelper(ResultSet resultSet) throws SQLException {
        Countries countries = new Countries();
        countries.setId(resultSet.getInt("id"));
        countries.setName(resultSet.getString("name"));
        return countries;
    }

    @Override
    public Countries getEntityById(int id) {
        String query = "SELECT * FROM countries WHERE id = ?";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    protected Countries getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Countries countries) {
        String query = "INSERT INTO countries (name) VALUES (?)";
        executeStatement(query, "saveEntity", countries);
    }

    @Override
    protected void saveEntityHelper(PreparedStatement preparedStatement, Countries countries) throws SQLException {
        preparedStatement.setString(1, countries.getName());
        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            countries.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(Countries countries) {
        String query = "UPDATE countries SET name = ? WHERE id = ?";
        executeStatement(query, "updateEntity", countries);
    }

    @Override
    protected void updateEntityHelper(PreparedStatement preparedStatement, Countries countries) throws SQLException {
        preparedStatement.setString(1, countries.getName());
        preparedStatement.setInt(2, countries.getId());
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
