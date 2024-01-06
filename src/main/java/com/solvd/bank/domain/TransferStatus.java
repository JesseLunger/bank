package com.solvd.bank.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "transferStatus")
@XmlType(propOrder = {"id", "status"})
public class TransferStatus {
    private int id;
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
