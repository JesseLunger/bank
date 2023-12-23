package com.solvd.bank.domain;

public class BranchHasEmployees {

    private Branches branch;
    private Staff staff;

    public enum BranchHasEmployeesColumns {
        BRANCH_ID("branch", "int"),
        STAFF_ID("staff", "int");

        private final String columnName;
        private final String columnType;

        BranchHasEmployeesColumns(String columnName, String columnType) {
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

    public Branches getBranch() {
        return branch;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Object getColumnValue(BranchHasEmployeesColumns column) {
        switch (column) {
            case BRANCH_ID:
                return branch.getId();
            case STAFF_ID:
                return staff.getAssociate().getId();
            default:
                return null;
        }
    }
}
