package com.solvd.bank.domain;

import com.solvd.bank.utils.xmlutils.TimeStampAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;

@XmlRootElement(name = "account")
@XmlType(propOrder = {"id", "branch", "customer", "amount", "dateCreated", "holds"})
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

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    @XmlElement(name = "branch")
    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Customer getCustomer() {
        return customer;
    }

    @XmlElement(name = "customer")
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    @XmlElement(name = "amount")
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    @XmlElement(name = "dateCreated")
    @XmlJavaTypeAdapter(TimeStampAdapter.class)
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isHolds() {
        return holds;
    }

    @XmlElement(name = "holds")
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
