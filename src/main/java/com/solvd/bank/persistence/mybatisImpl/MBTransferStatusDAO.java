package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.domain.TransferStatus;
import com.solvd.bank.persistence.ITransferStatusDAO;

import java.util.ArrayList;
import java.util.List;

public class MBTransferStatusDAO extends MBBaseClassDAO implements ITransferStatusDAO {

    @Override
    public ArrayList<Transaction> getTransactionsByStatusId(int id) {
        return null;
    }

    @Override
    public void saveEntity(TransferStatus transferStatus) {

    }

    @Override
    public TransferStatus getEntityById(int id) {
        return null;
    }

    @Override
    public void updateEntity(TransferStatus transferStatus) {

    }

    @Override
    public void removeEntityById(int id) {

    }

    @Override
    public List<TransferStatus> getAll() {
        return null;
    }
}
