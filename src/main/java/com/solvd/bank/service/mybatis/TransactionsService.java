package com.solvd.bank.service.mybatis;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.persistence.ITransactionDAO;
import com.solvd.bank.persistence.mybatisImpl.MBTransactionDAO;

import java.util.List;

public class TransactionsService {

    private ITransactionDAO transactionDAO;

    public TransactionsService() {
        this.transactionDAO = new MBTransactionDAO();
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
