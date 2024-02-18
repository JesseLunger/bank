package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.persistence.ICustomerDAO;
import com.solvd.bank.utils.jdbcconnectionutils.MySQLFactory;

import java.util.List;

public class CustomerDAO implements ICustomerDAO {

    private final ICustomerDAO mapper;

    public CustomerDAO() {
        mapper = MySQLFactory.getSqlSessionFactory().openSession(true).getMapper(ICustomerDAO.class);
    }

    @Override
    public void updateCreditScore(Customer customer, double newScore) {
        mapper.updateCreditScore(customer, newScore);
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
