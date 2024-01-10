package com.solvd.bank.utils.enums;

public enum StatusNames {
    ACCEPTED("accepted"),
    PENDING("pending"),
    DECLINED("declined");

    private final String STATUS;

    StatusNames(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSTATUS() {
        return STATUS;
    }
}
