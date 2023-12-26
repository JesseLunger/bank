package com.solvd.bank.service;

import com.solvd.bank.domain.TransferStatus;
import com.solvd.bank.persistence.ITransferStatusDAO;
import com.solvd.bank.persistence.impl.TransferStatusDAO;

import java.util.List;

public class TransferStatusesService {

    private final ITransferStatusDAO I_TRANSFER_STATUS_DAO;

    public TransferStatusesService() {
        this.I_TRANSFER_STATUS_DAO = new TransferStatusDAO();
    }

    public List<TransferStatus> getAllTransferStatuses() {
        return I_TRANSFER_STATUS_DAO.getAll();
    }

    public TransferStatus getTransferStatusById(int id) {
        return I_TRANSFER_STATUS_DAO.getEntityById(id);
    }

    public void saveTransferStatus(TransferStatus transferStatus) {
        I_TRANSFER_STATUS_DAO.saveEntity(transferStatus);
    }

    public void updateTransferStatus(TransferStatus transferStatus) {
        I_TRANSFER_STATUS_DAO.updateEntity(transferStatus);
    }

    public void removeTransferStatusById(int id) {
        I_TRANSFER_STATUS_DAO.removeEntityByID(id);
    }
}
