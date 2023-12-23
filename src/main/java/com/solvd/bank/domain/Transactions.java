package com.solvd.bank.domain;

import java.sql.Timestamp;

public class Transactions {
    private int id;
    private Cards card;
    private Merchants merchant;
    private int statusId;
    private Timestamp time;
    private double amount;

    public enum TransactionsColumns {
        ID("id", "int"),
        CARD_ID("card_id", "int"),
        MERCHANT_ID("merchant_id", "int"),
        STATUS_ID("status_id", "int"),
        TRANSACTION_TIME("transaction_time", "TimeStamp"),
        AMOUNT("amount", "double");

        private final String columnName;
        private final String columnType;

        TransactionsColumns(String columnName, String columnType) {
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

    public Cards getCard() {
        return card;
    }

    public void setCard(Cards card) {
        this.card = card;
    }

    public Merchants getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchants merchant) {
        this.merchant = merchant;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Object getColumnValue(TransactionsColumns column) {
        switch (column) {
            case ID:
                return id;
            case CARD_ID:
                return card.getId();
            case MERCHANT_ID:
                return merchant.getAssociate().getId();
            case STATUS_ID:
                return statusId;
            case TRANSACTION_TIME:
                return time;
            case AMOUNT:
                return amount;
            default:
                return null;
        }
    }
}
