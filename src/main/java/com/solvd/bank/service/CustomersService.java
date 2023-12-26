package com.solvd.bank.service;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.persistence.ICustomersDAO;
import com.solvd.bank.persistence.impl.CustomerDAO;

import java.util.List;

public class CustomersService {

    private final ICustomersDAO I_CUSTOMER_DAO;

    public CustomersService() {
        this.I_CUSTOMER_DAO = new CustomerDAO();
    }

    public List<Customer> getAllCustomers() {
        return I_CUSTOMER_DAO.getAll();
    }

    public Customer getCustomerById(int id) {
        return I_CUSTOMER_DAO.getEntityById(id);
    }

    public void saveCustomer(Customer customer) {
        I_CUSTOMER_DAO.saveEntity(customer);
    }

    public void updateCustomer(Customer customer) {
        I_CUSTOMER_DAO.updateEntity(customer);
    }

    public void removeCustomerById(int id) {
        I_CUSTOMER_DAO.removeEntityByID(id);
    }
}
