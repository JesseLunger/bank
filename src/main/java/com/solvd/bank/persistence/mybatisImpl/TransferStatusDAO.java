package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.domain.TransferStatus;
import com.solvd.bank.persistence.ITransferStatusDAO;
import com.solvd.bank.utils.MySQLFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransferStatusDAO implements ITransferStatusDAO {

    private final ITransferStatusDAO mapper;

    public TransferStatusDAO() {
        mapper = MySQLFactory.getSqlSessionFactory().openSession(true).getMapper(ITransferStatusDAO.class);
    }

    @Override
    public ArrayList<Transaction> getTransactionsByStatusId(int id) {
        return new TransactionDAO().getAll().stream()
                .filter(transaction -> transaction.getTransferStatus().getId() == id)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<TransferStatus> getAll() {
        return mapper.getAll();
    }

    @Override
    public TransferStatus getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(TransferStatus transferStatus) {
        mapper.saveEntity(transferStatus);
    }

    @Override
    public void updateEntity(TransferStatus transferStatus) {
        mapper.updateEntity(transferStatus);
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }
}
