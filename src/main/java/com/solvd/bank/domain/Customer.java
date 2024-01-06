package com.solvd.bank.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "customer")
@XmlType(propOrder = {"associate", "creditScore"})
public class Customer {
    private Associate associate;
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
