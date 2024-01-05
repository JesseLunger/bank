package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.domain.TransferStatus;
import com.solvd.bank.persistence.ITransactionDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;

import java.util.List;

public class TransactionDAO implements ITransactionDAO {

    private ITransactionDAO mapper;

    public TransactionDAO() {
        mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(ITransactionDAO.class);
    }

    @Override
    public void updateStatus(Transaction transaction, TransferStatus transferStatus) {
        transaction.setTransferStatus(transferStatus);
        mapper.updateEntity(transaction);
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
