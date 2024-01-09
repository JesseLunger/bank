package com.solvd.bank.persistence;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.domain.TransferStatus;

import java.util.ArrayList;

public interface ITransferStatusDAO extends IBaseDAO<TransferStatus> {

    ArrayList<Transaction> getTransactionsByStatusId(int id);

}
