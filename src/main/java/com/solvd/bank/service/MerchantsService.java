package com.solvd.bank.service;

import com.solvd.bank.domain.Merchant;
import com.solvd.bank.persistence.MerchantsRepository;
import com.solvd.bank.persistence.impl.MerchantsJdbcImpl;

import java.util.List;

public class MerchantsService {

    private final MerchantsRepository merchantsRepository;

    public MerchantsService() {
        this.merchantsRepository = new MerchantsJdbcImpl();
    }

    public List<Merchant> getAllMerchants() {
        return merchantsRepository.getAll();
    }

    public Merchant getMerchantById(int id) {
        return merchantsRepository.getEntityById(id);
    }

    public void saveMerchant(Merchant merchant) {
        merchantsRepository.saveEntity(merchant);
    }

    public void updateMerchant(Merchant merchant) {
        merchantsRepository.updateEntity(merchant);
    }

    public void removeMerchantById(int id) {
        merchantsRepository.removeEntityByID(id);
    }
}
