package com.mf.merchants.constant;

public enum ErrorCode {
    SUCCESS(0, ""),
    DUPLICATE_NAME(1, "duplicate"),
    EMPTY_LOGO(2, "log is empty"),
    EMPTY_BUSINESS_LICENSE(3, "business is empty"),
    ERROR_PHONE(4, "phone is wrong"),
    EMPTY_ADDRESS(5, "address is null"),
    MERCHANTS_NOT_EXIST(6, "not exist");

    private Integer code;
    private String desc;

    ErrorCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode () {
        return this.code;
    }

    public String getDesc () {
        return this.desc;
    }
}
