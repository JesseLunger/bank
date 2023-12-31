package com.solvd.bank.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlRootElement(name = "branch")
@XmlType(propOrder = {"id", "location", "branchName", "branchStaff"})
public class Branch {
    private int id;
    private Location location;
    private String branchName;
    private ArrayList<Staff> branchStaff;

    public int getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    @XmlElement(name = "location")
    public void setLocation(Location location) {
        this.location = location;
    }

    public String getBranchName() {
        return branchName;
    }

    @XmlElement(name = "branchName")
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public ArrayList<Staff> getBranchStaff() {
        return branchStaff;
    }

    @XmlElementWrapper(name = "branchStaff")
    @XmlElement(name = "staff")
    public void setBranchStaff(ArrayList<Staff> branchStaff) {
        this.branchStaff = branchStaff;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", location=" + location +
                ", branchName='" + branchName + '\'' +
                '}';
    }
}
