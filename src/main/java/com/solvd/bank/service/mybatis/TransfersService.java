package com.solvd.bank.service.mybatis;

import com.solvd.bank.domain.Transfer;
import com.solvd.bank.persistence.ITransferDAO;
import com.solvd.bank.persistence.mybatisImpl.TransferDAO;

import java.util.List;

public class TransfersService {

    private ITransferDAO transferDAO;

    public TransfersService() {
        this.transferDAO = new TransferDAO();
    }

    public List<Transfer> getAllTransfers() {
        return transferDAO.getAll();
    }

    public Transfer getTransferById(int id) {
        return transferDAO.getEntityById(id);
    }

    public void saveTransfer(Transfer transfer) {
        transferDAO.saveEntity(transfer);
    }

    public void updateTransfer(Transfer transfer) {
        transferDAO.updateEntity(transfer);
    }

    public void removeTransferById(int id) {
        transferDAO.removeEntityById(id);
    }
}
