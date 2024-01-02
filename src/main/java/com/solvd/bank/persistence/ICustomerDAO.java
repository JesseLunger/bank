package com.solvd.bank.persistence;

import com.solvd.bank.domain.Customer;

public interface ICustomerDAO extends IBaseDAO<Customer> {

    public void updateCreditScore(Customer customer, double newScore);
}
