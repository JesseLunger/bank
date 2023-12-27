package com.solvd.bank.persistence;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.domain.Merchant;

import java.util.ArrayList;

public interface IMerchantDAO extends IBaseDAO<Merchant> {
    ArrayList<Customer> getCustomersWithTransactions(Merchant merchant);

}
