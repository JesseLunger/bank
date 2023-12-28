package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Card;
import com.solvd.bank.domain.Transaction;
import com.solvd.bank.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDAO extends BaseClassDAO<Card> implements com.solvd.bank.persistence.ICardDAO {

    @Override
    public ArrayList<Transaction> getAllTransactions(Card card) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        TransactionDAO transactionDAO = new TransactionDAO();
        String query = "SELECT * FROM transactions where card_id = (?);";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, card.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    transactions.add(transactionDAO.createEntity(resultSet));
                }
            }
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return transactions;
    }

    @Override
    public List<Card> getAll() {
        String query = "SELECT * FROM cards;";
        return executeStatement(query, "getAll");
    }

    @Override
    public Card createEntity(ResultSet resultSet) throws SQLException {
        Card card = new Card();
        card.setId(resultSet.getInt("id"));
        card.setAccount(new AccountDAO().getEntityById(resultSet.getInt("account_id")));
        card.setCardNumber(resultSet.getString("card_number"));
        card.setExpirationDate(resultSet.getTimestamp("expiration_date"));
        card.setCvv(resultSet.getString("cvv"));
        return card;
    }

    @Override
    public Card getEntityById(int id) {
        String query = "SELECT * FROM cards WHERE id = (?);";
        return executeStatement(query, "getEntityById", id).get(0);
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void saveEntity(Card card) {
        String query = "INSERT INTO cards (account_id, card_number, expiration_date, cvv) " +
                "VALUES ((?), (?), (?), (?))";
        executeStatement(query, "saveEntity", card);
        Integer autoIncrementValue = getAutoIncrementValue();
        if (autoIncrementValue != null) {
            card.setId(autoIncrementValue);
        }
    }

    @Override
    protected void prepareSaveStatement(PreparedStatement preparedStatement, Card card) throws SQLException {
        preparedStatement.setInt(1, card.getAccount().getId());
        preparedStatement.setString(2, card.getCardNumber());
        preparedStatement.setTimestamp(3, card.getExpirationDate());
        preparedStatement.setString(4, card.getCvv());
    }

    @Override
    public void updateEntity(Card card) {
        String query = "UPDATE cards SET account_id = (?), card_number = (?), " +
                "expiration_date = (?), cvv = (?) WHERE id = (?);";
        executeStatement(query, "updateEntity", card);
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Card card) throws SQLException {
        preparedStatement.setInt(1, card.getAccount().getId());
        preparedStatement.setString(2, card.getCardNumber());
        preparedStatement.setTimestamp(3, card.getExpirationDate());
        preparedStatement.setString(4, card.getCvv());
        preparedStatement.setInt(5, card.getId());
    }

    @Override
    public void removeEntityById(int id) {
        String query = "DELETE FROM cards WHERE id = (?);";
        executeStatement(query, "removeEntityById", id);
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
    }
}
