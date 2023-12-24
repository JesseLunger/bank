package com.solvd.bank.service;

import com.solvd.bank.domain.Transfer;
import com.solvd.bank.persistence.TransfersRepository;
import com.solvd.bank.persistence.impl.TransfersJdbcImpl;

import java.util.List;

public class TransfersService {

    private final TransfersRepository transfersRepository;

    public TransfersService() {
        this.transfersRepository = new TransfersJdbcImpl();
    }

    public List<Transfer> getAllTransfers() {
        return transfersRepository.getAll();
    }

    public Transfer getTransferById(int id) {
        return transfersRepository.getEntityById(id);
    }

    public void saveTransfer(Transfer transfer) {
        transfersRepository.saveEntity(transfer);
    }

    public void updateTransfer(Transfer transfer) {
        transfersRepository.updateEntity(transfer);
    }

    public void removeTransferById(int id) {
        transfersRepository.removeEntityByID(id);
    }
}
