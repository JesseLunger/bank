package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.persistence.ITransactionDAO;
import com.solvd.bank.utils.jdbcconnectionutils.MySQLFactory;

import java.util.List;

public class TransactionDAO implements ITransactionDAO {

    private final ITransactionDAO mapper;

    public TransactionDAO() {
        mapper = MySQLFactory.getSqlSessionFactory().openSession(true).getMapper(ITransactionDAO.class);
    }

    @Override
    public void updateStatus(Transaction transaction) {
        mapper.updateStatus(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        return mapper.getAll();
    }

    @Override
    public Transaction getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(Transaction transaction) {
        mapper.saveEntity(transaction);
    }

    @Override
    public void updateEntity(Transaction transaction) {
        mapper.updateEntity(transaction);
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }
}
