package com.solvd.bank.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlRootElement(name = "branch")
@XmlType(propOrder = {"id", "location", "branchName", "branchStaff"})
@JsonRootName("branch")
public class Branch {

    @JsonProperty("id")
    private int id;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("branchName")
    private String branchName;

    @JsonProperty("branchStaff")
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
