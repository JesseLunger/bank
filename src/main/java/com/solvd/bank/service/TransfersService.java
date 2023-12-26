package com.solvd.bank.service;

import com.solvd.bank.domain.Transfer;
import com.solvd.bank.persistence.ITransferDAO;
import com.solvd.bank.persistence.impl.TransferDAO;

import java.util.List;

public class TransfersService {

    private final ITransferDAO I_TRANSFER_DAO;

    public TransfersService() {
        this.I_TRANSFER_DAO = new TransferDAO();
    }

    public List<Transfer> getAllTransfers() {
        return I_TRANSFER_DAO.getAll();
    }

    public Transfer getTransferById(int id) {
        return I_TRANSFER_DAO.getEntityById(id);
    }

    public void saveTransfer(Transfer transfer) {
        I_TRANSFER_DAO.saveEntity(transfer);
    }

    public void updateTransfer(Transfer transfer) {
        I_TRANSFER_DAO.updateEntity(transfer);
    }

    public void removeTransferById(int id) {
        I_TRANSFER_DAO.removeEntityByID(id);
    }
}
