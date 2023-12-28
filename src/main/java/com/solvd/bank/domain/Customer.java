package com.solvd.bank.domain;

public class Customer {
    private Associate associate;
    private double creditScore;

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public double getCreditScore() {
        return creditScore;
    }

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
