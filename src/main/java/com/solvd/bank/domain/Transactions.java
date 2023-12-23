package com.solvd.bank.domain;

import java.sql.Timestamp;

public class Transactions {
    private int id;
    private Cards card;
    private Merchants merchant;
    private int statusId;
    private Timestamp time;
    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cards getCard() {
        return card;
    }

    public void setCard(Cards card) {
        this.card = card;
    }

    public Merchants getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchants merchant) {
        this.merchant = merchant;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
