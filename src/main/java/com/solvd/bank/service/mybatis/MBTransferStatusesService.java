package com.solvd.bank.service.mybatis;

import com.solvd.bank.domain.TransferStatus;
import com.solvd.bank.persistence.ITransferStatusDAO;
import com.solvd.bank.persistence.impl.TransferStatusDAO;

import java.util.List;

public class MBTransferStatusesService {

    private ITransferStatusDAO transferStatusDAO;

    public MBTransferStatusesService() {
        this.transferStatusDAO = new TransferStatusDAO();
    }

    public List<TransferStatus> getAllTransferStatuses() {
        return transferStatusDAO.getAll();
    }

    public TransferStatus getTransferStatusById(int id) {
        return transferStatusDAO.getEntityById(id);
    }

    public void saveTransferStatus(TransferStatus transferStatus) {
        transferStatusDAO.saveEntity(transferStatus);
    }

    public void updateTransferStatus(TransferStatus transferStatus) {
        transferStatusDAO.updateEntity(transferStatus);
    }

    public void removeTransferStatusById(int id) {
        transferStatusDAO.removeEntityById(id);
    }
}
