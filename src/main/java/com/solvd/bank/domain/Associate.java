package com.solvd.bank.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.bank.utils.xmlutils.TimeStampAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;

@XmlRootElement(name = "associate")
@XmlType(propOrder = {"id", "location", "primaryName", "secondaryName", "dateJoined", "email", "phoneNumber"})
@JsonRootName("associate")
public class Associate {

    @JsonProperty("id")
    private int id;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("primaryName")
    private String primaryName;

    @JsonProperty("secondaryName")
    private String secondaryName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm a z", timezone = "America/Los_Angeles")
    @JsonProperty("dateJoined")
    private Timestamp dateJoined;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
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

