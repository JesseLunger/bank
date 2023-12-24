package com.solvd.bank.domain;

import java.sql.Timestamp;

public class Transfer {

    private int id;
    private Account sender;
    private Account receiver;
    private int statusId;
    private Timestamp transferTime;
    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
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
