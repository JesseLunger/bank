package com.solvd.bank.persistence;

import com.solvd.bank.domain.Transaction;

public interface ITransactionDAO extends IBaseDAO<Transaction> {

    void updateStatus(Transaction transaction);

}
