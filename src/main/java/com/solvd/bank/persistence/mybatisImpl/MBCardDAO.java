package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Card;
import com.solvd.bank.domain.Transaction;
import com.solvd.bank.persistence.ICardDAO;

import java.util.ArrayList;
import java.util.List;

public class MBCardDAO extends MBBaseClassDAO implements ICardDAO {
    @Override
    public void saveEntity(Card card) {

    }

    @Override
    public Card getEntityById(int id) {
        return null;
    }

    @Override
    public void updateEntity(Card card) {

    }

    @Override
    public void removeEntityById(int id) {

    }

    @Override
    public List<Card> getAll() {
        return null;
    }

    @Override
    public ArrayList<Transaction> getAllTransactions(Card card) {
        return null;
    }
}
