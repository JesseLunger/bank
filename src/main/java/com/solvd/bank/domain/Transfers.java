package com.solvd.bank.domain;

import java.sql.Timestamp;

public class Transfers {

    private int id;
    private Accounts sender;
    private Accounts receiver;
    private int statusId;
    private Timestamp transferTime;
    private double amount;

    public enum TransfersColumns {
        ID("id", "int"),
        SENDER_ID("sender_id", "int"),
        RECEIVER_ID("receiver_id", "int"),
        STATUS_ID("status_id", "int"),
        TRANSFER_TIME("transfer_time", "TimeStamp"),
        AMOUNT("amount", "double");

        private final String columnName;
        private final String columnType;

        TransfersColumns(String columnName, String columnType) {
            this.columnName = columnName;
            this.columnType = columnType;
        }

        public String getColumnName() {
            return columnName;
        }

        public String getColumnType() {
            return columnType;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Accounts getSender() {
        return sender;
    }

    public void setSender(Accounts sender) {
        this.sender = sender;
    }

    public Accounts getReceiver() {
        return receiver;
    }

    public void setReceiver(Accounts receiver) {
        this.receiver = receiver;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Timestamp getTime() {
        return transferTime;
    }

    public void setTime(Timestamp time) {
        this.transferTime = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Object getColumnValue(TransfersColumns column) {
        switch (column) {
            case ID:
                return id;
            case SENDER_ID:
                return sender.getId();
            case RECEIVER_ID:
                return receiver.getId();
            case STATUS_ID:
                return statusId;
            case TRANSFER_TIME:
                return transferTime;
            case AMOUNT:
                return amount;
            default:
                return null;
        }
    }
}
