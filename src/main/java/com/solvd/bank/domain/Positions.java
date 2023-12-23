package com.solvd.bank.domain;

public class Positions {

    private int id;
    private String position;
    private double salary;
    private double hourlyWage;

    public enum PositionsColumns {
        ID("id", "int"),
        POSITION("position", "String"),
        SALARY("salary", "double"),
        HOURLY_WAGE("hourly_wage", "double");

        private final String columnName;
        private final String columnType;

        PositionsColumns(String columnName, String columnType) {
            this.columnName = columnName;
            this.columnType = columnType;
        }

        public String getColumnName() {
            return columnName;
        }

        public String getColumnType() {
            return columnType;
        }
    }


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

    public Object getColumnValue(PositionsColumns column) {
        switch (column) {
            case ID:
                return id;
            case POSITION:
                return position;
            case SALARY:
                return salary;
            case HOURLY_WAGE:
                return hourlyWage;
            default:
                return null;
        }
    }

}
