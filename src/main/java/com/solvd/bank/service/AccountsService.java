package com.solvd.bank.service;

import com.solvd.bank.domain.Account;
import com.solvd.bank.persistence.IAccountDAO;
import com.solvd.bank.persistence.impl.AccountDAO;

import java.util.List;

public class AccountsService {
    private final IAccountDAO I_ACCOUNTS_DAO;

    public AccountsService(){
        this.I_ACCOUNTS_DAO = new AccountDAO();
    }

    public void addAmount(Account account, double amountToAdd){
        I_ACCOUNTS_DAO.addAmount(account, amountToAdd);
    }

    public List<Account> getAllAccounts(){
        return I_ACCOUNTS_DAO.getAll();
    }

    public Account getAccountById(int id){
        return I_ACCOUNTS_DAO.getEntityById(id);
    }

    public void saveAccount(Account account){
        I_ACCOUNTS_DAO.saveEntity(account);
    }

    public void updateAccount(Account account){
        I_ACCOUNTS_DAO.updateEntity(account);
    }

    public void removeAccountById(int id){
        I_ACCOUNTS_DAO.removeEntityByID(id);
    }
}
