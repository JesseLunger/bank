package com.solvd.bank.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "position")
@XmlType(propOrder = {"id", "position", "salary", "hourlyWage"})
@JsonRootName("position")
public class Position {

    @JsonProperty("id")
    private int id;

    @JsonProperty("position")
    private String position;

    @JsonProperty("salary")
    private double salary;

    @JsonProperty("hourlyWage")
    private double hourlyWage;

    public int getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    @XmlElement(name = "position")
    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    @XmlElement(name = "salary")
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    @XmlElement(name = "hourlyWage")
    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", hourlyWage=" + hourlyWage +
                '}';
    }
}
