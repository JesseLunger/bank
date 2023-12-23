package com.solved.bank.domain;

public class Countries {

    private int id;
    private String name;

    public enum CountriesColumns {
        ID("id", "int"),
        NAME("name", "String");

        private final String columnName;
        private final String columnType;

        CountriesColumns(String columnName, String columnType) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getColumnValue(CountriesColumns column) {
        switch (column) {
            case ID:
                return id;
            case NAME:
                return name;
            default:
                return null;
        }
    }
}
