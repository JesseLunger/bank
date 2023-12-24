package com.solvd.bank.service;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.persistence.TransactionsRepository;
import com.solvd.bank.persistence.impl.TransactionsJdbcImpl;

import java.util.List;

public class TransactionsService {

    private final TransactionsRepository transactionsRepository;

    public TransactionsService() {
        this.transactionsRepository = new TransactionsJdbcImpl();
    }

    public List<Transaction> getAllTransactions() {
        return transactionsRepository.getAll();
    }

    public Transaction getTransactionById(int id) {
        return transactionsRepository.getEntityById(id);
    }

    public void saveTransaction(Transaction transaction) {
        transactionsRepository.saveEntity(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        transactionsRepository.updateEntity(transaction);
    }

    public void removeTransactionById(int id) {
        transactionsRepository.removeEntityByID(id);
    }
}
