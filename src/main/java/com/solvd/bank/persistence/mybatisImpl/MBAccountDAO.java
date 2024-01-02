package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Account;
import com.solvd.bank.persistence.IAccountDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;

import java.util.List;

public class MBAccountDAO implements IAccountDAO {

    private IAccountDAO mapper;

    public MBAccountDAO() {
        mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(IAccountDAO.class);
    }

    @Override
    public void addAmount(Account account, double amount) {
        account.setAmount(account.getAmount() + amount);
        updateEntity(account);
    }

    @Override
    public List<Account> getAll() {
        return mapper.getAll();
    }

    @Override
    public Account getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(Account account) {
        mapper.saveEntity(account);
    }

    @Override
    public void updateEntity(Account account) {
        mapper.updateEntity(account);
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }
}
