package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Transfer;
import com.solvd.bank.persistence.ITransferDAO;
import com.solvd.bank.utils.MySQLFactory;

import java.util.List;

public class TransferDAO implements ITransferDAO {

    private ITransferDAO mapper;

    public TransferDAO() {
        mapper = MySQLFactory.getSqlSessionFactory().openSession(true).getMapper(ITransferDAO.class);
    }

    @Override
    public void removeDeclinedTransfers(Transfer transfer) {
        if (transfer.getTransferStatus().getStatus().equals("declined")) {
            mapper.removeEntityById(transfer.getId());
        }
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
