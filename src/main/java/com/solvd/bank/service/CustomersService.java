package com.solvd.bank.service;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.persistence.CustomersRepository;
import com.solvd.bank.persistence.impl.CustomersJdbcImpl;

import java.util.List;

public class CustomersService {

    private final CustomersRepository customersRepository;

    public CustomersService() {
        this.customersRepository = new CustomersJdbcImpl();
    }

    public List<Customer> getAllCustomers() {
        return customersRepository.getAll();
    }

    public Customer getCustomerById(int id) {
        return customersRepository.getEntityById(id);
    }

    public void saveCustomer(Customer customer) {
        customersRepository.saveEntity(customer);
    }

    public void updateCustomer(Customer customer) {
        customersRepository.updateEntity(customer);
    }

    public void removeCustomerById(int id) {
        customersRepository.removeEntityByID(id);
    }
}
