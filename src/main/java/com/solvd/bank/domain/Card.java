package com.solvd.bank.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.bank.utils.xmlutils.TimeStampAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;

@XmlRootElement(name = "card")
@XmlType(propOrder = {"id", "account", "cardNumber", "expirationDate", "cvv"})
@JsonRootName("card")
public class Card {

    @JsonProperty("id")
    private int id;

    @JsonProperty("account")
    private Account account;

    @JsonProperty("cardNumber")
    private String cardNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy", timezone = "America/Los_Angeles")
    @JsonProperty("expirationDate")
    private Timestamp expirationDate;

    @JsonProperty("cvv")
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
