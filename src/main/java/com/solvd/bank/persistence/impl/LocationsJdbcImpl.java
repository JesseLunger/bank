package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Locations;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocationsJdbcImpl extends BaseClassJdbcImpl<Locations> implements IBaseRepository<Locations> {

    @Override
    protected Locations getAllHelper(ResultSet resultSet) {
        Locations location = new Locations();
        try {
            location.setId(resultSet.getInt("id"));
            location.setCity(new CitiesJdbcImpl().getEntityById(resultSet.getInt("city_id")));
            location.setZipCode(resultSet.getString("zip_code"));
            location.setAddress(resultSet.getString("address"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return location;
    }

    @Override
    protected Locations getEntityByIdHelper(PreparedStatement preparedStatement, int id) {
        try {
            preparedStatement.setInt(1, id);
            return processResultSet(preparedStatement).get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    protected void saveEntityHelper(PreparedStatement preparedStatement, Locations location) {
        try {
            preparedStatement.setInt(1, location.getCity().getId());
            preparedStatement.setString(2, location.getZipCode());
            preparedStatement.setString(3, location.getAddress());

            int AICheck = preparedStatement.executeUpdate();
            if (AICheck > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    location.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    protected void updateEntityHelper(PreparedStatement preparedStatement, Locations location) {
        try {
            int counter = 1;
            for (Locations.LocationsColumns column : Locations.LocationsColumns.values()) {
                parseEnum(counter, column.getColumnType(), location.getColumnValue(column), preparedStatement);
                counter++;
            }
            preparedStatement.setInt(counter, location.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    protected void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) {
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void saveEntity(Locations location) {
        String query = "INSERT INTO locations (city_id, zip_code, address) VALUES (?, ?, ?)";
        executeStatement(query, "saveEntity", location);
    }

    @Override
    public Locations getEntityById(int id) {
        String query = "SELECT * FROM locations WHERE id = ?";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public void updateEntity(Locations location) {
        StringBuilder query = new StringBuilder("UPDATE locations SET ");
        for (Locations.LocationsColumns column : Locations.LocationsColumns.values()) {
            query.append(column.getColumnName()).append(" = ?, ");
        }
        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        executeStatement(query.toString(), "updateEntity", location);
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM locations WHERE id = ?";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public List<Locations> getAll() {
        String query = "SELECT * FROM locations";
        return executeStatement(query, "getAll");
    }
}
