package com.solvd.bank.domain;

import com.solvd.bank.utils.xmlutils.TimeStampAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;

@XmlRootElement(name = "transaction")
@XmlType(propOrder = {"id", "card", "merchant", "transferStatus", "transactionTime", "amount"})
public class Transaction {
    private int id;
    private Card card;
    private Merchant merchant;
    private TransferStatus transferStatus;
    private Timestamp transactionTime;
    private double amount;

    public int getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    @XmlElement(name = "card")
    public void setCard(Card card) {
        this.card = card;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    @XmlElement(name = "merchant")
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }

    @XmlElement(name = "transferStatus")
    public void setTransferStatus(TransferStatus transferStatus) {
        this.transferStatus = transferStatus;
    }

    public Timestamp getTransactionTime() {
        return transactionTime;
    }

    @XmlElement(name = "transactionTime")
    @XmlJavaTypeAdapter(TimeStampAdapter.class)
    public void setTransactionTime(Timestamp transactionTime) {
        this.transactionTime = transactionTime;
    }

    public double getAmount() {
        return amount;
    }

    @XmlElement(name = "amount")
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", card=" + card +
                ", merchant=" + merchant +
                ", transferStatus=" + transferStatus +
                ", time=" + transactionTime +
                ", amount=" + amount +
                '}';
    }
}
