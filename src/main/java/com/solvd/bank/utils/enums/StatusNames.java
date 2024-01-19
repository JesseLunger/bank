package com.solvd.bank.utils.enums;

public enum StatusNames {
    ACCEPTED("accepted"),
    PENDING("pending"),
    DECLINED("declined");

    private final String status;

    StatusNames(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
