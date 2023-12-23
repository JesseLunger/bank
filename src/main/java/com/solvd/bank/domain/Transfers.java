package com.solvd.bank.domain;

import java.sql.Timestamp;

public class Transfers {

    private int id;
    private Accounts sender;
    private Accounts receiver;
    private int statusId;
    private Timestamp transferTime;
    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Accounts getSender() {
        return sender;
    }

    public void setSender(Accounts sender) {
        this.sender = sender;
    }

    public Accounts getReceiver() {
        return receiver;
    }

    public void setReceiver(Accounts receiver) {
        this.receiver = receiver;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Timestamp getTime() {
        return transferTime;
    }

    public void setTime(Timestamp time) {
        this.transferTime = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
