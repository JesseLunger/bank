package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.domain.Merchant;
import com.solvd.bank.persistence.IMerchantDAO;

import java.util.ArrayList;
import java.util.List;

public class MBMerchantDAO extends MBBaseClassDAO implements IMerchantDAO {
    @Override
    public void saveEntity(Merchant merchant) {

    }

    @Override
    public Merchant getEntityById(int id) {
        return null;
    }

    @Override
    public void updateEntity(Merchant merchant) {

    }

    @Override
    public void removeEntityById(int id) {

    }

    @Override
    public List<Merchant> getAll() {
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomersWithTransactions(Merchant merchant) {
        return null;
    }
}
