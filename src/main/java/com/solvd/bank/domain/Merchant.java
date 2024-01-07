package com.solvd.bank.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "merchant")
@XmlType(propOrder = {"associate"})
@JsonRootName("merchant")
public class Merchant {

    @JsonProperty("associate")
    private Associate associate;

    public Associate getAssociate() {
        return associate;
    }

    @XmlElement(name = "associate")
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
