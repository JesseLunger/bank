package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Countries;
import com.solvd.bank.persistence.CountriesRepository;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.*;
import java.util.List;

public class CountriesJdbcImpl extends BaseClassJdbcImpl<Countries> implements IBaseRepository<Countries>, CountriesRepository{

    @Override
    protected Countries getAllHelper(ResultSet resultSet) throws SQLException {
        Countries countries = new Countries();
        countries.setId(resultSet.getInt("id"));
        countries.setName(resultSet.getString("name"));
        return countries;
    }


    @Override
    protected Countries getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    protected void saveEntityHelper(PreparedStatement preparedStatement, Countries countries) throws SQLException {
        preparedStatement.setString(1, countries.getName());
        int AICheck = preparedStatement.executeUpdate();
        if (AICheck > 0){
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                countries.setId(generatedKeys.getInt(1));
            }
        }
    }

    @Override
    protected void updateEntityHelper(PreparedStatement preparedStatement, Countries countries) throws SQLException{
        int counter = 1;
        for (Countries.CountriesColumns column : Countries.CountriesColumns.values()) {
            parseEnum(counter, column.getColumnType(), countries.getColumnValue(column), preparedStatement);
            counter++;
        }
        preparedStatement.setInt(counter, countries.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    protected void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public void saveEntity(Countries countries) {
       String query = "INSERT INTO countries (name) VALUES ((?))";
       executeStatement(query, "saveEntity", countries);
    }


    @Override
    public Countries getEntityById(int id) {
        String query = "SELECT * FROM countries WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public void updateEntity(Countries countries) {
        StringBuilder query = new StringBuilder("UPDATE countries SET ");
        for (Countries.CountriesColumns column : Countries.CountriesColumns.values()) {
            query.append(column.getColumnName()).append(" = (?), ");
        }
        query.setLength(query.length() - 2);
        query.append(" WHERE id = (?);");
        executeStatement(query.toString(), "updateEntity", countries);
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM countries WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public List<Countries> getAll() {
        String query = "SELECT * FROM countries;";
        return executeStatement(query, "getAll");
    }
}
