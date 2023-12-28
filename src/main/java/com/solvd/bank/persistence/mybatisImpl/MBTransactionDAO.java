package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.domain.TransferStatus;
import com.solvd.bank.persistence.ITransactionDAO;

import java.util.List;

public class MBTransactionDAO extends MBBaseClassDAO implements ITransactionDAO {

    @Override
    public void updateStatus(Transaction transaction, TransferStatus transferStatus) {

    }

    @Override
    public void saveEntity(Transaction transaction) {

    }

    @Override
    public Transaction getEntityById(int id) {
        return null;
    }

    @Override
    public void updateEntity(Transaction transaction) {

    }

    @Override
    public void removeEntityById(int id) {

    }

    @Override
    public List<Transaction> getAll() {
        return null;
    }

}
