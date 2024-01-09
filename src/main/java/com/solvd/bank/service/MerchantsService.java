package com.solvd.bank.service;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.domain.Merchant;
import com.solvd.bank.persistence.IMerchantDAO;
import com.solvd.bank.persistence.mybatisImpl.MerchantDAO;

import java.util.ArrayList;
import java.util.List;

public class MerchantsService {

    private IMerchantDAO merchantDAO;

    public MerchantsService() {
        this.merchantDAO = new MerchantDAO();
    }

    public ArrayList<Customer> getCustomersWithTransactions(Merchant merchant) {
        return merchantDAO.getCustomersWithTransactions(merchant);
    }

    public List<Merchant> getAllMerchants() {
        return merchantDAO.getAll();
    }

    public Merchant getMerchantById(int id) {
        return merchantDAO.getEntityById(id);
    }

    public void saveMerchant(Merchant merchant) {
        merchantDAO.saveEntity(merchant);
    }

    public void updateMerchant(Merchant merchant) {
        merchantDAO.updateEntity(merchant);
    }

    public void removeMerchantById(int id) {
        merchantDAO.removeEntityById(id);
    }
}
