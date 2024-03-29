package com.solvd.bank.service;

import com.solvd.bank.domain.Account;
import com.solvd.bank.persistence.IAccountDAO;
import com.solvd.bank.persistence.mybatisImpl.AccountDAO;

import java.util.List;

public class AccountsService {
    private final IAccountDAO accountDAO = new AccountDAO();

    public void addAmount(Account account, double amountToAdd) {
        accountDAO.addAmount(account, amountToAdd);
    }

    public List<Account> getAllAccounts() {
        return accountDAO.getAll();
    }

    public Account getAccountById(int id) {
        return accountDAO.getEntityById(id);
    }

    public void saveAccount(Account account) {
        accountDAO.saveEntity(account);
    }

    public void updateAccount(Account account) {
        accountDAO.updateEntity(account);
    }

    public void removeAccountById(int id) {
        accountDAO.removeEntityById(id);
    }
}
