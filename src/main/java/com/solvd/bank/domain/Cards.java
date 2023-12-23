package com.solvd.bank.domain;

import java.sql.Timestamp;

public class Cards {
    private int id;
    private Accounts account;
    private String cardNumber;
    private Timestamp expirationDate;
    private String cvv;

    public enum CardsColumns {
        ID("id", "int"),
        ACCOUNT_ID("account_id", "int"),
        CARD_NUMBER("card_number", "String"),
        EXPIRATION_DATE("expiration_date", "TimeStamp"),
        CVV("cvv", "String");

        private final String columnName;
        private final String columnType;

        CardsColumns(String columnName, String columnType) {
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

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Object getColumnValue(CardsColumns column) {
        switch (column) {
            case ID:
                return id;
            case ACCOUNT_ID:
                return account.getId();
            case CARD_NUMBER:
                return cardNumber;
            case EXPIRATION_DATE:
                return expirationDate;
            case CVV:
                return cvv;
            default:
                return null;
        }
    }
}
