package com.solved.bank.persistence.impl;

import com.solved.bank.domain.Associates;
import com.solved.bank.persistence.IBaseRepository;
import com.solved.bank.utils.DBConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AssociatesJdbcImpl extends BaseClassJdbcImpl<Associates> implements IBaseRepository<Associates> {

    @Override
    protected Associates getAllHelper(ResultSet resultSet) throws SQLException{
        Associates associate = new Associates();
        associate.setId(resultSet.getInt("id"));
        associate.setLocation(new LocationsJdbcImpl().getEntityById(resultSet.getInt("location_id")));
        associate.setPrimaryName(resultSet.getString("primary_name"));
        associate.setSecondaryName(resultSet.getString("secondary_name"));
        associate.setDateJoined(resultSet.getTimestamp("date_joined"));
        associate.setEmail(resultSet.getString("email"));
        associate.setPhoneNumber(resultSet.getString("phone_number"));
        return associate;
    }

    @Override
    protected Associates getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException{
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    protected void saveEntityHelper(PreparedStatement preparedStatement, Associates associate) throws SQLException{
        int counter = 1;
        for (Associates.AssociatesColumns column : Associates.AssociatesColumns.values()) {
            parseEnum(counter, column.getColumnType(), associate.getColumnValue(column), preparedStatement);
            counter++;
        }
        int AICheck = preparedStatement.executeUpdate();
        if (AICheck > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                associate.setId(generatedKeys.getInt(1));
            }
        }
    }


    @Override
    protected void updateEntityHelper(PreparedStatement preparedStatement, Associates associate) {
        try {
            int counter = 1;
            for (Associates.AssociatesColumns column : Associates.AssociatesColumns.values()) {
                parseEnum(counter, column.getColumnType(), associate.getColumnValue(column), preparedStatement);
                counter++;
            }
            preparedStatement.setInt(counter, associate.getId());
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
    public void saveEntity(Associates associate) {
        String query = "INSERT INTO associates (location_id, primary_name, secondary_name, date_joined, email, phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        executeStatement(query, "saveEntity", associate);
    }

    @Override
    public Associates getEntityById(int id) {
        String query = "SELECT * FROM associates WHERE id = ?";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public void updateEntity(Associates associate) {
        StringBuilder query = new StringBuilder("UPDATE associates SET ");
        for (Associates.AssociatesColumns column : Associates.AssociatesColumns.values()) {
            query.append(column.getColumnName()).append(" = (?), ");
        }
        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        executeStatement(query.toString(), "updateEntity", associate);
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM associates WHERE id = ?";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public List<Associates> getAll() {
        String query = "SELECT * FROM associates";
        return executeStatement(query, "getAll");
    }
}
