package com.solved.bank.domain;

public class TransferStatus {
    private int id;
    private String status;

    public enum TransferStatusColumns {
        ID("id", "int"),
        STATUS("status", "String");

        private final String columnName;
        private final String columnType;

        TransferStatusColumns(String columnName, String columnType) {
            this.columnName = columnName;
            this.columnType = columnType;
        }

        public String getColumnName() {
            return columnName;
        }

        public String getColumnType(){
            return columnType;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getColumnValue(TransferStatusColumns column) {
        switch (column) {
            case ID:
                return id;
            case STATUS:
                return status;
            default:
                return null;
        }
    }

}
