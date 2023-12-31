package com.solvd.bank.domain;

import com.solvd.bank.utils.xmlutils.TimeStampAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;

@XmlRootElement(name = "card")
@XmlType(propOrder = {"id", "account", "cardNumber", "expirationDate", "cvv"})
public class Card {
    private int id;
    private Account account;
    private String cardNumber;
    private Timestamp expirationDate;
    private String cvv;

    public int getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    @XmlElement(name = "account")
    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @XmlElement(name = "cardNumber")
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    @XmlElement(name = "expirationDate")
    @XmlJavaTypeAdapter(TimeStampAdapter.class)
    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    @XmlElement(name = "cvv")
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", account=" + account +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}
