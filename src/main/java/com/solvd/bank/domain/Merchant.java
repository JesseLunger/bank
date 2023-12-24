package com.solvd.bank.domain;

public class Merchant {

    private Associate associate;

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "associate=" + associate +
                '}';
    }
}
