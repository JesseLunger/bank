package com.solvd.bank.domain;

import java.sql.Timestamp;

public class Staff {
    private Associate associate;
    private Position position;
    private Timestamp dateHired;

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Timestamp getDateHired() {
        return dateHired;
    }

    public void setDateHired(Timestamp dateHired) {
        this.dateHired = dateHired;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "associate=" + associate +
                ", position=" + position +
                ", dateHired=" + dateHired +
                '}';
    }
}