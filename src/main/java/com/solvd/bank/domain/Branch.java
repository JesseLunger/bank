package com.solvd.bank.domain;

import java.util.ArrayList;

public class Branch {
    private int id;
    private Location location;
    private String branchName;

    private ArrayList<Staff> branchStaff;

    public ArrayList<Staff> getBranchStaff() {
        return branchStaff;
    }

    public void setBranchStaff(ArrayList<Staff> branchStaff) {
        this.branchStaff = branchStaff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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
