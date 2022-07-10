package com.company.enums;

public enum ErrorCodeEnum {
    BOOK_NOT_FOUND(1001, "Can not find book with given id"),
    VALIDATION_ERROR(1002, "  is not valid"),

    UNKNOWN_ERROR(1003, "unknown error");
    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return this.code;
    }

}
