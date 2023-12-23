package com.solved.bank.domain;

public class Merchants {

    private Associates associate;

    public enum MerchantsColumns {
        ASSOCIATE_ID("associate_ID", "int");

        private final String columnName;
        private final String columnType;

        MerchantsColumns(String columnName, String columnType) {
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

    public Object getColumnValue(MerchantsColumns column) {
        switch (column) {
            case ASSOCIATE_ID:
                return associate.getId();
            default:
                return null;
        }
    }
}
