package com.solvd.bank.domain;

public class BranchHasEmployee {

    private Branch branch;
    private Staff staff;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "BranchHasEmployee{" +
                "branch=" + branch +
                ", staff=" + staff +
                '}';
    }
}
