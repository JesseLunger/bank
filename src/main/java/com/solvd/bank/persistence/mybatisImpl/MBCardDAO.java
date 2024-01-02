package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Card;
import com.solvd.bank.domain.Transaction;
import com.solvd.bank.persistence.ICardDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MBCardDAO implements ICardDAO {

    private ICardDAO mapper;

    public MBCardDAO() {
        mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(ICardDAO.class);
    }

    @Override
    public ArrayList<Transaction> getAllTransactionsByCard(Card card) {
        return new MBTransactionDAO().getAll().stream()
                .filter(transaction -> transaction.getCard().getId() == card.getId())
                .collect(Collectors.toCollection(ArrayList::new));
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
