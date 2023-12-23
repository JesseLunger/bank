package com.solvd.bank.domain;

import java.sql.Timestamp;

public class Accounts {

    private int id;
    private Branches branch;
    private Customers customer;
    private double amount;
    private Timestamp dateCreated;
    private boolean holds;

    public enum AccountsColumns {
        ID("id", "int"),
        BRANCH_ID("branch_id", "int"),
        CUSTOMER_ID("customer_id", "int"),
        AMOUNT("amount", "double"),
        DATE_CREATED("date_created", "Timestamp"),
        HOLDS("holds", "boolean");

        private final String columnName;
        private final String columnType;

        AccountsColumns(String columnName, String columnType) {
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

    public Branches getBranch() {
        return branch;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isHolds() {
        return holds;
    }

    public void setHolds(boolean holds) {
        this.holds = holds;
    }

    public Object getColumnValue(AccountsColumns column) {
        switch (column) {
            case ID:
                return id;
            case BRANCH_ID:
                return branch.getId();
            case CUSTOMER_ID:
                return customer.getAssociate().getId();
            case AMOUNT:
                return amount;
            case DATE_CREATED:
                return dateCreated;
            case HOLDS:
                return holds;
            default:
                return null;
        }
    }
}
