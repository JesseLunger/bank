package com.solvd.bank.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "position")
@XmlType(propOrder = {"id", "position", "salary", "hourlyWage"})
public class Position {

    private int id;
    private String position;
    private double salary;
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
