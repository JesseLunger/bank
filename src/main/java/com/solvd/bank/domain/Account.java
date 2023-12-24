package com.solvd.bank.domain;

import java.sql.Timestamp;

public class Account {

    private int id;
    private Branch branch;
    private Customer customer;
    private double amount;
    private Timestamp dateCreated;
    private boolean holds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isHolds() {
        return holds;
    }

    public void setHolds(boolean holds) {
        this.holds = holds;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", branch=" + branch +
                ", customer=" + customer +
                ", amount=" + amount +
                ", dateCreated=" + dateCreated +
                ", holds=" + holds +
                '}';
    }

}
