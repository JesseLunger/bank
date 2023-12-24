package com.solvd.bank.domain;

public class Customer {
    private Associate associate;
    private String creditScore;

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
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
