package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Transfer;
import com.solvd.bank.persistence.ITransferDAO;

import java.util.List;

public class MBTransferDAO extends MBBaseClassDAO implements ITransferDAO {

    @Override
    public void removeDeclinedTransfers(Transfer transfer) {

    }

    @Override
    public void saveEntity(Transfer transfer) {

    }

    @Override
    public Transfer getEntityById(int id) {
        return null;
    }

    @Override
    public void updateEntity(Transfer transfer) {

    }

    @Override
    public void removeEntityById(int id) {

    }

    @Override
    public List<Transfer> getAll() {
        return null;
    }
}
