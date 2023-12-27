package com.solvd.bank.persistence;

import com.solvd.bank.domain.Card;
import com.solvd.bank.domain.Transaction;

import java.util.ArrayList;

public interface ICardDAO extends IBaseDAO<Card> {

    ArrayList<Transaction> getAllTransactions(Card card);

}
