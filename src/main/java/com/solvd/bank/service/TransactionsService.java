package com.solvd.bank.service;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.persistence.ITransactionDAO;
import com.solvd.bank.persistence.mybatisImpl.TransactionDAO;

import java.util.List;

public class TransactionsService {

    private final ITransactionDAO transactionDAO;

    public TransactionsService() {
        this.transactionDAO = new TransactionDAO();
    }

    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAll();
    }

    public Transaction getTransactionById(int id) {
        return transactionDAO.getEntityById(id);
    }

    public void saveTransaction(Transaction transaction) {
        transactionDAO.saveEntity(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        transactionDAO.updateEntity(transaction);
    }

    public void removeTransactionById(int id) {
        transactionDAO.removeEntityById(id);
    }
}
