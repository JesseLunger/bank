package com.solvd.bank.persistence;

import com.solvd.bank.domain.Transfer;

public interface ITransferDAO extends IBaseDAO<Transfer> {

    void removeDeclinedTransfers(Transfer transfer);

}
