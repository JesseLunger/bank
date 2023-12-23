package com.solvd.bank.domain;

public class Customers {
    private Associates associate;
    private String creditScore;

    public Associates getAssociate() {
        return associate;
    }

    public void setAssociate(Associates associate) {
        this.associate = associate;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
    }

}
