package com.solved.bank.domain;

import java.sql.Timestamp;

public class Associates {
    private int id;
    private Locations location;
    private String primaryName;
    private String secondaryName;
    private Timestamp dateJoined;
    private String email;
    private String phoneNumber;

    public enum AssociatesColumns {
        ID("id", "int"),
        LOCATION_ID("location_id", "int"),
        PRIMARY_NAME("primary_name", "String"),
        SECONDARY_NAME("secondary_name", "String"),
        DATE_JOINED("date_joined", "Timestamp"),
        EMAIL("email", "String"),
        PHONE_NUMBER("phone_number", "String");

        private final String columnName;

        private final String columnType;

        AssociatesColumns(String columnName, String columnType) {
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

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public void setSecondaryName(String secondaryName) {
        this.secondaryName = secondaryName;
    }

    public Timestamp getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Timestamp dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getColumnValue(AssociatesColumns column) {
        switch (column) {
            case ID:
                return id;
            case LOCATION_ID:
                return location.getId();
            case PRIMARY_NAME:
                return primaryName;
            case SECONDARY_NAME:
                return secondaryName;
            case DATE_JOINED:
                return dateJoined;
            case EMAIL:
                return email;
            case PHONE_NUMBER:
                return phoneNumber;
            default:
                return null;
        }
    }
}
