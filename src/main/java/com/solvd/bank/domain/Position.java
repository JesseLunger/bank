package com.solvd.bank.domain;

public class Position {

    private int id;
    private String position;
    private double salary;
    private double hourlyWage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

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
