package com.solvd.bank.domain;

public class Branches {
    private int id;
    private Locations location;
    private String branchName;

    public enum BranchesColumns {
        ID("id", "int"),
        LOCATION_ID("location_id", "int"),
        BRANCH_NAME("branch_name", "String");

        private final String columnName;
        private final String columnType;

        BranchesColumns(String columnName, String columnType) {
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Object getColumnValue(BranchesColumns column) {
        switch (column) {
            case ID:
                return id;
            case LOCATION_ID:
                return location.getId();
            case BRANCH_NAME:
                return branchName;
            default:
                return null;
        }
    }
}
