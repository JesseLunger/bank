package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.persistence.ICustomersDAO;

import java.util.List;

public class MBCustomerDAO extends MBBaseClassDAO implements ICustomersDAO {
    @Override
    public void saveEntity(Customer customer) {

    }

    @Override
    public Customer getEntityById(int id) {
        return null;
    }

    @Override
    public void updateEntity(Customer customer) {

    }

    @Override
    public void removeEntityById(int id) {

    }

    @Override
    public List<Customer> getAll() {
        return null;
    }
}
