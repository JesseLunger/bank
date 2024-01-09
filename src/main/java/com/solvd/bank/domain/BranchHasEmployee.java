package com.solvd.bank.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "branchHasEmployee")
@XmlType(propOrder = {"branch", "staff"})
public class BranchHasEmployee {

    private Branch branch;
    private Staff staff;

    public Branch getBranch() {
        return branch;
    }

    @XmlElement(name = "branch")
    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Staff getStaff() {
        return staff;
    }

    @XmlElement(name = "staff")
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
