package com.solvd.bank.service;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.persistence.ICustomerDAO;
import com.solvd.bank.persistence.mybatisImpl.CustomerDAO;

import java.util.List;

public class CustomersService {

    private final ICustomerDAO customersDAO;

    public CustomersService() {
        this.customersDAO = new CustomerDAO();
    }

    public List<Customer> getAllCustomers() {
        return customersDAO.getAll();
    }

    public Customer getCustomerById(int id) {
        return customersDAO.getEntityById(id);
    }

    public void saveCustomer(Customer customer) {
        customersDAO.saveEntity(customer);
    }

    public void updateCustomer(Customer customer) {
        customersDAO.updateEntity(customer);
    }

    public void removeCustomerById(int id) {
        customersDAO.removeEntityById(id);
    }
}
