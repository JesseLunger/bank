package com.solvd.bank.domain;

import java.sql.Timestamp;

public class Staff {
    private Associates associate;
    private Positions position;
    private Timestamp dateHired;

    public Associates getAssociate() {
        return associate;
    }

    public void setAssociate(Associates associate) {
        this.associate = associate;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public Timestamp getDateHired() {
        return dateHired;
    }

    public void setDateHired(Timestamp dateHired) {
        this.dateHired = dateHired;
    }

}