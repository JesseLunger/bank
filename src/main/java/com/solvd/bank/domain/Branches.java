package com.solvd.bank.domain;

public class Branches {
    private int id;
    private Locations location;
    private String branchName;

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

}
