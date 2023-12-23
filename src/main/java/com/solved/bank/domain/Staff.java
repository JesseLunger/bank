package com.solved.bank.domain;

import java.sql.Timestamp;

public class Staff {
    private Associates associate;
    private Positions position;
    private Timestamp dateHired;

    public enum StaffColumns {
        ASSOCIATE_ID("associate", "int"),
        POSITION_ID("position_id", "int"),
        DATE_HIRED("date_hired", "Timestamp");

        private final String columnName;
        private final String columnType;

        StaffColumns(String columnName, String columnType) {
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

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public Timestamp getDateHired() {
        return dateHired;
    }

    public void setDateHired(Timestamp dateHired) {
        this.dateHired = dateHired;
    }

    public Object getColumnValue(StaffColumns column) {
        switch (column) {
            case ASSOCIATE_ID:
                return associate.getId();
            case POSITION_ID:
                return position.getId();
            case DATE_HIRED:
                return dateHired;
            default:
                return null;
        }
    }
}
