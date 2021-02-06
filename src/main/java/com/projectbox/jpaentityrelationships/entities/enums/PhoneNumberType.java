package com.projectbox.jpaentityrelationships.entities.enums;

public enum PhoneNumberType {

    HOME("H"), MOBILE("M"), WORK("W");

    private String code;

    private PhoneNumberType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
