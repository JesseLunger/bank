package com.solved.bank.domain;

public class Cities {
    private int id;
    private Countries country;
    private String name;

    public enum CitiesColumns {
        ID("id", "int"),
        COUNTRY_ID("country_id", "int"),
        NAME("name", "String");

        private final String columnName;
        private final String columnType;

        CitiesColumns(String columnName, String columnType) {
            this.columnName = columnName;
            this.columnType = columnType;
        }

        public String getColumnName() {
            return columnName;
        }

        public String getColumnType(){
            return columnType;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getColumnValue(CitiesColumns column) {
        switch (column) {
            case ID:
                return id;
            case COUNTRY_ID:
                return country.getId();
            case NAME:
                return name;
            default:
                return null;
        }
    }
}
