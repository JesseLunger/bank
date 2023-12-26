package com.solvd.bank.service;

import com.solvd.bank.domain.Card;
import com.solvd.bank.domain.Transaction;
import com.solvd.bank.persistence.ICardDAO;
import com.solvd.bank.persistence.impl.CardDAO;

import java.util.ArrayList;
import java.util.List;

public class CardsService {

    private final ICardDAO I_CARD_DAO;

    public CardsService() {
        this.I_CARD_DAO = new CardDAO();
    }

    public ArrayList<Transaction> getAllTransActionsByCard(Card card){
        return I_CARD_DAO.getAllTransactions(card);
    }

    public List<Card> getAllCards() {
        return I_CARD_DAO.getAll();
    }

    public Card getCardById(int id) {
        return I_CARD_DAO.getEntityById(id);
    }

    public void saveCard(Card card) {
        I_CARD_DAO.saveEntity(card);
    }

    public void updateCard(Card card) {
        I_CARD_DAO.updateEntity(card);
    }

    public void removeCardById(int id) {
        I_CARD_DAO.removeEntityByID(id);
    }
}
