package com.solvd.bank.domain;

import com.solvd.bank.utils.xmlutils.TimeStampAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;

@XmlRootElement(name = "associate")
@XmlType(propOrder = {"id", "location", "primaryName", "secondaryName", "dateJoined", "email", "phoneNumber"})
public class Associate {
    private int id;
    private Location location;
    private String primaryName;
    private String secondaryName;
    private Timestamp dateJoined;
    private String email;
    private String phoneNumber;

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

    public String getPrimaryName() {
        return primaryName;
    }
    
    @XmlElement(name = "primaryName")
    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    @XmlElement(name = "secondaryName")
    public void setSecondaryName(String secondaryName) {
        this.secondaryName = secondaryName;
    }

    public Timestamp getDateJoined() {
        return dateJoined;
    }

    @XmlElement(name = "dateJoined")
    @XmlJavaTypeAdapter(TimeStampAdapter.class)
    public void setDateJoined(Timestamp dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @XmlElement(name = "phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Associate{" +
                "id=" + id +
                ", location=" + location +
                ", primaryName='" + primaryName + '\'' +
                ", secondaryName='" + secondaryName + '\'' +
                ", dateJoined=" + dateJoined +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

