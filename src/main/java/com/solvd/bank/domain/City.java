package com.solvd.bank.domain;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "city")
@XmlType(propOrder = {"id", "country", "name"})
public class City {

    private int id;
    private Country country;
    private String name;

    public int getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    @XmlElement(name = "country")
    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", country=" + country +
                ", name='" + name + '\'' +
                '}';
    }
}
