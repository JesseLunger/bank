package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Cards;
import com.solvd.bank.persistence.IBaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class CardsJdbcImpl extends BaseClassJdbcImpl<Cards> implements IBaseRepository<Cards> {

    @Override
    public List<Cards> getAll() {
        String query = "SELECT * FROM cards;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Cards getAllHelper(ResultSet resultSet) throws SQLException {
        Cards card = new Cards();
        card.setId(resultSet.getInt("id"));
        card.setAccount(new AccountsJdbcImpl().getEntityById(resultSet.getInt("account_id")));
        card.setCardNumber(resultSet.getString("card_number"));
        card.setExpirationDate(resultSet.getTimestamp("expiration_date"));
        card.setCvv(resultSet.getString("cvv"));
        return card;
    }

    @Override
    public Cards getEntityById(int id) {
        String query = "SELECT * FROM cards WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    public Cards getEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return processResultSet(preparedStatement).get(0);
    }

    @Override
    public void saveEntity(Cards card) {
        String query = "INSERT INTO cards (account_id, card_number, expiration_date, cvv) " +
                "VALUES ((?), (?), (?), (?))";
        executeStatement(query, "saveEntity", card);
    }

    @Override
    public void saveEntityHelper(PreparedStatement preparedStatement, Cards card) throws SQLException {
        preparedStatement.setInt(1, card.getAccount().getId());
        preparedStatement.setString(2, card.getCardNumber());
        preparedStatement.setTimestamp(3, card.getExpirationDate());
        preparedStatement.setString(4, card.getCvv());

        Integer autoIncrementValue = getAutoIncrementValue(preparedStatement);
        if (autoIncrementValue != null) {
            card.setId(autoIncrementValue);
        }
    }

    @Override
    public void updateEntity(Cards card) {
        String query = "UPDATE cards SET account_id = (?), card_number = (?), " +
                "expiration_date = (?), cvv = (?) WHERE id = (?);";
        executeStatement(query, "updateEntity", card);
    }

    @Override
    public void updateEntityHelper(PreparedStatement preparedStatement, Cards card) throws SQLException {
        preparedStatement.setInt(1, card.getAccount().getId());
        preparedStatement.setString(2, card.getCardNumber());
        preparedStatement.setTimestamp(3, card.getExpirationDate());
        preparedStatement.setString(4, card.getCvv());
        preparedStatement.setInt(5, card.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeEntityByID(int id) {
        String query = "DELETE FROM cards WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    public void removeEntityByIdHelper(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
