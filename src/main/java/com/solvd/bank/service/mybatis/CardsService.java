package com.solvd.bank.service.mybatis;

import com.solvd.bank.domain.Card;
import com.solvd.bank.domain.Transaction;
import com.solvd.bank.persistence.ICardDAO;

import java.util.ArrayList;
import java.util.List;

public class CardsService {

    private ICardDAO cardDAO;

    public CardsService() {
        this.cardDAO = new MBCardDAO();
    }

    public ArrayList<Transaction> getAllTransActionsByCard(Card card) {
        return cardDAO.getAllTransactionsByCard(card);
    }

    public List<Card> getAllCards() {
        return cardDAO.getAll();
    }

    public Card getCardById(int id) {
        return cardDAO.getEntityById(id);
    }

    public void saveCard(Card card) {
        cardDAO.saveEntity(card);
    }

    public void updateCard(Card card) {
        cardDAO.updateEntity(card);
    }

    public void removeCardById(int id) {
        cardDAO.removeEntityById(id);
    }
}
