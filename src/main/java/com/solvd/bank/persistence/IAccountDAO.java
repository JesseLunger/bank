package com.solvd.bank.persistence;

import com.solvd.bank.domain.Account;

public interface IAccountDAO extends IBaseDAO<Account> {

    void addAmount(Account account, double amount);

}
