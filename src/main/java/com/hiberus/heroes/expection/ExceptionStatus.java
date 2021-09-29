package com.hiberus.heroes.expection;

public enum ExceptionStatus {
    BAD_INPUT("Bad input"),
    ERROR("Error"),
    INTERNAL_ERROR("Internal error"),
    DOCUMENT_NOT_FOUND("Document not found"),
    INCONSISTENT_DATA("Inconsistent data"),
    USER_HAS_NO_ACCESS("User has no access");

    private final String status;

    ExceptionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}