package com.solvd.bank.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "customer")
@XmlType(propOrder = {"associate", "creditScore"})
@JsonRootName("customer")
public class Customer {

    @JsonProperty("associate")
    private Associate associate;

    @JsonProperty("creditScore")
    private double creditScore;

    public Associate getAssociate() {
        return associate;
    }

    @XmlElement(name = "associate")
    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public double getCreditScore() {
        return creditScore;
    }

    @XmlElement(name = "creditScore")
    public void setCreditScore(double creditScore) {
        this.creditScore = creditScore;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "associate=" + associate +
                ", creditScore='" + creditScore + '\'' +
                '}';
    }
}
