package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.persistence.ICustomerDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;

import java.util.List;

public class MBCustomerDAO implements ICustomerDAO {

    private ICustomerDAO mapper;

    public MBCustomerDAO() {
        mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(ICustomerDAO.class);
    }

    @Override
    public void updateCreditScore(Customer customer, double newScore) {
        customer.setCreditScore(newScore);
        updateEntity(customer);
    }

    @Override
    public List<Customer> getAll() {
        return mapper.getAll();
    }

    @Override
    public Customer getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(Customer customer) {
        mapper.saveEntity(customer);
    }

    @Override
    public void updateEntity(Customer customer) {
        mapper.updateEntity(customer);
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }
}
