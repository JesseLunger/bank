package com.solvd.bank.service;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.persistence.ITransactionDAO;
import com.solvd.bank.persistence.impl.TransactionDAO;

import java.util.List;

public class TransactionsService {

    private final ITransactionDAO I_TRANSACTION_DAO;

    public TransactionsService() {
        this.I_TRANSACTION_DAO = new TransactionDAO();
    }

    public List<Transaction> getAllTransactions() {
        return I_TRANSACTION_DAO.getAll();
    }

    public Transaction getTransactionById(int id) {
        return I_TRANSACTION_DAO.getEntityById(id);
    }

    public void saveTransaction(Transaction transaction) {
        I_TRANSACTION_DAO.saveEntity(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        I_TRANSACTION_DAO.updateEntity(transaction);
    }

    public void removeTransactionById(int id) {
        I_TRANSACTION_DAO.removeEntityByID(id);
    }
}
