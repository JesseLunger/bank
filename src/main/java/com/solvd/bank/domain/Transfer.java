package com.solvd.bank.domain;

import com.solvd.bank.utils.xmlutils.TimeStampAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;

@XmlRootElement(name = "transfer")
@XmlType(propOrder = {"id", "sender", "receiver", "transferStatus", "amount"})
public class Transfer {

    private int id;
    private Account sender;
    private Account receiver;
    private TransferStatus transferStatus;
    private Timestamp transferTime;
    private double amount;

    public int getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public Account getSender() {
        return sender;
    }

    @XmlElement(name = "sender")
    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    @XmlElement(name = "receiver")
    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }

    @XmlElement(name = "transferStatus")
    public void setTransferStatus(TransferStatus transferStatus) {
        this.transferStatus = transferStatus;
    }

    public Timestamp getTransferTime() {
        return transferTime;
    }

    @XmlElement(name = "transferTime")
    @XmlJavaTypeAdapter(TimeStampAdapter.class)
    public void setTransferTime(Timestamp time) {
        this.transferTime = time;
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
        return "Transfer{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", statusId=" + transferStatus +
                ", transferTime=" + transferTime +
                ", amount=" + amount +
                '}';
    }
}
