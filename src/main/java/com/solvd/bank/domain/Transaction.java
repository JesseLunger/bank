package com.solvd.bank.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.solvd.bank.utils.xmlutils.TimeStampAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;

@XmlRootElement(name = "transaction")
@XmlType(propOrder = {"id", "card", "merchant", "transferStatus", "transactionTime", "amount"})
@JsonRootName("transaction")
public class Transaction {

    @JsonProperty("id")
    private int id;

    @JsonProperty("card")
    private Card card;

    @JsonProperty("merchant")
    private Merchant merchant;

    @JsonProperty("transferStatus")
    private TransferStatus transferStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm a z", timezone = "America/Los_Angeles")
    @JsonProperty("transactionTime")
    private Timestamp transactionTime;

    @JsonProperty("amount")
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
