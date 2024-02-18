package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Transfer;
import com.solvd.bank.persistence.ITransferDAO;
import com.solvd.bank.utils.jdbcconnectionutils.MySQLFactory;

import java.util.List;

public class TransferDAO implements ITransferDAO {

    private final ITransferDAO mapper;

    public TransferDAO() {
        mapper = MySQLFactory.getSqlSessionFactory().openSession(true).getMapper(ITransferDAO.class);
    }

    @Override
    public void removeDeclinedTransfers() {
        mapper.removeDeclinedTransfers();
    }

    @Override
    public List<Transfer> getAll() {
        return mapper.getAll();
    }

    @Override
    public Transfer getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(Transfer transfer) {
        mapper.saveEntity(transfer);
    }

    @Override
    public void updateEntity(Transfer transfer) {
        mapper.updateEntity(transfer);
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }
}
