package com.savy.util;


public enum ResultStatus {
    SUCCESS(200, "success"),
    FAIL(400, "fail"),
    NO_DATA(500, "no data"),
    PARAM_ERROR(300, "param error");

    private int code;
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
