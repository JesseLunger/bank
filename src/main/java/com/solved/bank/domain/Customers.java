package com.solved.bank.domain;

public class Customers {
    private Associates associate;
    private String creditScore;

    public enum CustomersColumns {
        ASSOCIATE_ID("associate", "int"),
        CREDIT_SCORE("creditScore", "String");

        private final String columnName;
        private final String columnType;

        CustomersColumns(String columnName, String columnType) {
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

    public Associates getAssociate() {
        return associate;
    }

    public void setAssociate(Associates associate) {
        this.associate = associate;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
    }

    public Object getColumnValue(CustomersColumns column) {
        switch (column) {
            case ASSOCIATE_ID:
                return associate.getId();
            case CREDIT_SCORE:
                return creditScore;
            default:
                return null;
        }
    }
}
