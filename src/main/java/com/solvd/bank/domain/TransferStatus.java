package com.solvd.bank.domain;

public class TransferStatus {
    private int id;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

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
