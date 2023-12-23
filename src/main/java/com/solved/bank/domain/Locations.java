package com.solved.bank.domain;

public class Locations {

    private int id;
    private Cities city;
    private String zipCode;
    private String address;

    public enum LocationsColumns {
        ID("id", "int"),
        CITY_ID("city_id", "int"),
        ZIP_CODE("zip_code", "String"),
        ADDRESS("address", "String");

        private final String columnName;
        private final String columnType;

        LocationsColumns(String columnName, String columnType) {
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

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getColumnValue(LocationsColumns column) {
        switch (column) {
            case ID:
                return id;
            case CITY_ID:
                return city.getId();
            case ZIP_CODE:
                return zipCode;
            case ADDRESS:
                return address;
            default:
                return null;
        }
    }

}
