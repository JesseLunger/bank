package com.solvd.bank.service;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.domain.Merchant;
import com.solvd.bank.persistence.IMerchantDAO;
import com.solvd.bank.persistence.impl.MerchantDAO;

import java.util.ArrayList;
import java.util.List;

public class MerchantsService {

    private final IMerchantDAO I_MERCHANT_DAO;

    public MerchantsService() {
        this.I_MERCHANT_DAO = new MerchantDAO();
    }

    public ArrayList<Customer> getCustomersWithTransactions(Merchant merchant){
        return I_MERCHANT_DAO.getCustomersWithTransactions(merchant);
    }

    public List<Merchant> getAllMerchants() {
        return I_MERCHANT_DAO.getAll();
    }

    public Merchant getMerchantById(int id) {
        return I_MERCHANT_DAO.getEntityById(id);
    }

    public void saveMerchant(Merchant merchant) {
        I_MERCHANT_DAO.saveEntity(merchant);
    }

    public void updateMerchant(Merchant merchant) {
        I_MERCHANT_DAO.updateEntity(merchant);
    }

    public void removeMerchantById(int id) {
        I_MERCHANT_DAO.removeEntityByID(id);
    }
}
