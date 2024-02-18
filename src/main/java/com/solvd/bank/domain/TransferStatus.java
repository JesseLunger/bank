package com.solvd.bank.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "transferStatus")
@XmlType(propOrder = {"id", "status"})
@JsonRootName("transferStatus")
public class TransferStatus {

    @JsonProperty("id")
    private int id;

    @JsonProperty("status")
    private String status;

    public int getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    @XmlElement(name = "status")
    public void setStatus(String transferStatus) {
        this.status = transferStatus;
    }

    @Override
    public String toString() {
        return "TransferStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
