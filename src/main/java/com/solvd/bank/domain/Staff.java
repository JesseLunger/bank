package com.solvd.bank.domain;

import com.solvd.bank.utils.xmlutils.TimeStampAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;

@XmlRootElement(name = "staff")
@XmlType(propOrder = {"associate", "position", "dateHired"})
public class Staff {
    private Associate associate;
    private Position position;
    private Timestamp dateHired;

    public Associate getAssociate() {
        return associate;
    }

    @XmlElement(name = "associate")
    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public Position getPosition() {
        return position;
    }

    @XmlElement(name = "position")
    public void setPosition(Position position) {
        this.position = position;
    }

    public Timestamp getDateHired() {
        return dateHired;
    }

    @XmlElement(name = "dateHired")
    @XmlJavaTypeAdapter(TimeStampAdapter.class)
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