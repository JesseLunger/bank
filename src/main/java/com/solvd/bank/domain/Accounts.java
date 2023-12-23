package com.solvd.bank.domain;

import java.sql.Timestamp;

public class Accounts {

    private int id;
    private Branches branch;
    private Customers customer;
    private double amount;
    private Timestamp dateCreated;
    private boolean holds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Branches getBranch() {
        return branch;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
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

}
