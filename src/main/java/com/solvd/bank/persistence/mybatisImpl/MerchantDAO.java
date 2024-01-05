package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.domain.Merchant;
import com.solvd.bank.persistence.IMerchantDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;

import java.util.ArrayList;
import java.util.List;

public class MerchantDAO implements IMerchantDAO {

    private IMerchantDAO mapper;

    public MerchantDAO() {
        mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(IMerchantDAO.class);
    }

    @Override
    public ArrayList<Customer> getCustomersWithTransactions(Merchant merchant) {
        return mapper.getCustomersWithTransactions(merchant);
    }

    @Override
    public List<Merchant> getAll() {
        return mapper.getAll();
    }

    @Override
    public Merchant getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(Merchant merchant) {
        mapper.saveEntity(merchant);
    }

    @Override
    public void updateEntity(Merchant merchant) {
        new AssociateDAO().updateEntity(merchant.getAssociate());
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }

}
