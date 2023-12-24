package com.solvd.bank.service;

import com.solvd.bank.domain.TransferStatus;
import com.solvd.bank.persistence.TransferStatusesRepository;
import com.solvd.bank.persistence.impl.TransferStatusesJdbcImpl;

import java.util.List;

public class TransferStatusesService {

    private final TransferStatusesRepository transferStatusesRepository;

    public TransferStatusesService() {
        this.transferStatusesRepository = new TransferStatusesJdbcImpl();
    }

    public List<TransferStatus> getAllTransferStatuses() {
        return transferStatusesRepository.getAll();
    }

    public TransferStatus getTransferStatusById(int id) {
        return transferStatusesRepository.getEntityById(id);
    }

    public void saveTransferStatus(TransferStatus transferStatus) {
        transferStatusesRepository.saveEntity(transferStatus);
    }

    public void updateTransferStatus(TransferStatus transferStatus) {
        transferStatusesRepository.updateEntity(transferStatus);
    }

    public void removeTransferStatusById(int id) {
        transferStatusesRepository.removeEntityByID(id);
    }
}
