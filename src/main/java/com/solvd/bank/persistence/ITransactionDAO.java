package com.solvd.bank.persistence;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.domain.TransferStatus;

public interface ITransactionDAO extends IBaseDAO<Transaction> {

    void updateStatus(Transaction transaction);

}
