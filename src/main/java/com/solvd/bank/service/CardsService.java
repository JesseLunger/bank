package com.solvd.bank.service;

import com.solvd.bank.domain.Card;
import com.solvd.bank.persistence.CardsRepository;
import com.solvd.bank.persistence.impl.CardsJdbcImpl;

import java.util.List;

public class CardsService {

    private final CardsRepository cardsRepository;

    public CardsService() {
        this.cardsRepository = new CardsJdbcImpl();
    }

    public List<Card> getAllCards() {
        return cardsRepository.getAll();
    }

    public Card getCardById(int id) {
        return cardsRepository.getEntityById(id);
    }

    public void saveCard(Card card) {
        cardsRepository.saveEntity(card);
    }

    public void updateCard(Card card) {
        cardsRepository.updateEntity(card);
    }

    public void removeCardById(int id) {
        cardsRepository.removeEntityByID(id);
    }
}
