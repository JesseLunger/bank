package com.solvd.bank.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "location")
@XmlType(propOrder = {"id", "city", "zipCode", "address"})
public class Location {

    private int id;
    private City city;
    private String zipCode;
    private String address;

    public int getId() {
        return id;
    }
    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    @XmlElement(name = "city")
    public void setCity(City city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    @XmlElement(name = "zipCode")
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement(name = "address")
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", city=" + city +
                ", zipCode='" + zipCode + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
