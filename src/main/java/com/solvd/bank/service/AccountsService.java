package com.solvd.bank.service;

import com.solvd.bank.domain.Account;
import com.solvd.bank.persistence.AccountsRepository;
import com.solvd.bank.persistence.impl.AccountsJdbcImpl;

import java.util.List;

public class AccountsService {
    private final AccountsRepository accountsRepository;

    public AccountsService(){
        this.accountsRepository = new AccountsJdbcImpl();
    }

    public List<Account> getAllAccounts(){
        return accountsRepository.getAll();
    }

    public Account getAccountById(int id){
        return accountsRepository.getEntityById(id);
    }

    public void saveAccount(Account account){
        accountsRepository.saveEntity(account);
    }

    public void updateAccount(Account account){
        accountsRepository.updateEntity(account);
    }

    public void removeAccountById(int id){
        accountsRepository.removeEntityByID(id);
    }
}
