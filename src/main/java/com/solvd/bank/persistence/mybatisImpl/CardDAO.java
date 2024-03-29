package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Card;
import com.solvd.bank.domain.Transaction;
import com.solvd.bank.persistence.ICardDAO;
import com.solvd.bank.utils.jdbcconnectionutils.MySQLFactory;

import java.util.ArrayList;
import java.util.List;

public class CardDAO implements ICardDAO {

    private final ICardDAO mapper;

    public CardDAO() {
        mapper = MySQLFactory.getSqlSessionFactory().openSession(true).getMapper(ICardDAO.class);
    }

    @Override
    public ArrayList<Transaction> getAllTransactionsByCard(Card card) {
        return mapper.getAllTransactionsByCard(card);
    }

    @Override
    public List<Card> getAll() {
        return mapper.getAll();
    }

    @Override
    public Card getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(Card card) {
        mapper.saveEntity(card);
    }

    @Override
    public void updateEntity(Card card) {
        mapper.updateEntity(card);
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }
}
